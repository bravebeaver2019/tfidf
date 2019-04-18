package com.test3.tfidf.listener;

import com.test3.tfidf.Document;
import com.test3.tfidf.indexer.SimpleCountIndexer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class ListenerImpl implements Listener {

    @Autowired
    SimpleCountIndexer simpleCountIndexer;

    @Override
    public void index(Document document) {
        log.info("Indexing new document... " + document.getName());
        List<String> words = Arrays.asList(document.getContent().split(" "));
        words.forEach( word -> {
            simpleCountIndexer.index(word);
        });
    }
}
