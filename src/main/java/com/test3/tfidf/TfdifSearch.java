package com.test3.tfidf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TfdifSearch {

    @Value("${tfidf.search.limit}")
    Integer limit;

    @Autowired
    Tfidf tfidf;

    public List<SearchRank> search(String term) {
        List<SearchRank> ranks = tfidf.getDocuments().stream().map(
                document -> {
                    double tdidf = 1;
                    List<String> strings = Arrays.asList(term.split(" "));
                    for (String string : strings) {
                        tdidf = tdidf * tfidf.tdidf(document, string);
                    }
                    return new SearchRank(tdidf, term, document);
                }
        ).sorted(Comparator.comparing(SearchRank::getFrequency).reversed()).limit(limit).collect(Collectors.toList());
        return ranks;
    }
}
