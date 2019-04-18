package com.test3.tfidf.indexer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SimpleCountIndexer {

    @Autowired
    @Qualifier("SimpleCount")
    Map<String,Integer> simpleCount;

    public void index(String word) {
        if(simpleCount.containsKey(word)) {
            simpleCount.put(word, simpleCount.get(word) + 1);
        } else {
            simpleCount.put(word, 1);
        }
    }
}
