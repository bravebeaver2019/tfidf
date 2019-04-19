package com.test3.tfidf.notifier;

import com.test3.tfidf.SearchRank;
import com.test3.tfidf.Tfidf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Slf4j
/**
 * This component performs a periodical term search and displays results.
 */
public class TfidfNotifier {

    @Value("${tfidf.search.term}")
    String term;
    @Value("${tfidf.search.limit}")
    Integer limit;

    @Autowired
    Set<Tfidf> tfidfs;

    @Scheduled(fixedDelayString = "${tfidf.search.period}")
    public void notifyResult() {
        tfidfs.stream().forEach(tfidf -> {
            log.info("======================= [" + tfidf.getClass() + "] =======================");
            log.info("----------------------- [" + term + "] term frequency -----------------------");
            // compute rank for each document, then sort by tdidf frequency
            List<SearchRank> ranks = tfidf.getDocuments().stream().map(
                    // todo: refactor to extract method and DEEP test
                    document -> {
                        double tdidf = 1;
                        List<String> strings = Arrays.asList(term.split(" "));
                        for (String string : strings) {
                            tdidf = tdidf * tfidf.tdidf(document, string);
                        }
                        return new SearchRank(tdidf, term, document);
                    }
            ).sorted(Comparator.comparing(SearchRank::getFrequency).reversed()).collect(Collectors.toList());
            // order by tfidf frequency, take the <limit> best results and log them
            ranks.stream().filter( r -> r.getFrequency()>0 ).limit(limit).forEach(r -> log.info(r.toString()));
            log.info("----------------------- [" + term + "] term frequency -----------------------");
            log.info("======================= [" + tfidf.getClass() + "] =======================");
        });
    }

}
