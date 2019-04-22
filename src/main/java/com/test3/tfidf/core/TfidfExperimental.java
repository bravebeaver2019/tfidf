package com.test3.tfidf.core;

import com.test3.tfidf.Document;
import com.test3.tfidf.Tfidf;
import com.test3.tfidf.listener.Listener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@Slf4j
public class TfidfExperimental implements Listener, Tfidf {

    @Value("${tfidf.tokenizer.delimiters}")
    String delimiters;

    Set<Document> documents = new HashSet<>();

    @Override
    public void index(Document document) {
        log.info("added document: " + document.getName());
        documents.add(document);
    }

    @Override
    public double tf(Document document, String term) {
        double count = (double) document.words(delimiters).stream().filter(s-> s.equalsIgnoreCase(term) ).count();
        return (count / (double) document.words(delimiters).stream().count()) * 3; // obviously wrong
    }

    @Override
    public double idf(String term) {
        double count = 0;
        for(Document document: documents) {
            for(String word: document.words(delimiters)) {
                if(term.equalsIgnoreCase(word)) {
                    count ++;
                    break;
                }
            }
        }
        if(count>0)
            return Math.log( documents.size() / count) * 3; // obviously wrong
        else
            return 0;
    }

    @Override
    public double tdidf(Document document, String term) {
        return tf(document, term) * idf(term);
    }

    @Override
    public Set<Document> getDocuments() {
        return documents;
    }
}
