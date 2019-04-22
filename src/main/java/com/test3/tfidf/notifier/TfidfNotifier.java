package com.test3.tfidf.notifier;

import com.test3.tfidf.SearchRank;
import com.test3.tfidf.TfdifSearch;
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

    @Autowired
    TfdifSearch search;

    @Scheduled(fixedDelayString = "${tfidf.search.period}")
    public void notifyResult() {
            log.info("----------------------- [" + term + "] term frequency -----------------------");
            search.search(term).stream().forEach( res -> log.info(res.toString()));
            log.info("----------------------- [" + term + "] term frequency -----------------------");
    }

}
