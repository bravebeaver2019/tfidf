package com.test3.tfidf.listener;

import com.test3.tfidf.Document;
import com.test3.tfidf.Tfidf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class TfidfImpl implements Listener, Tfidf {

    List<Document> documents = new ArrayList<>();

    @Override
    public void index(Document document) {
        log.info("added document: " + document.getName());
        documents.add(document);
    }

    @Override
    public double tf(Document document, String term) {
        double count = (double) document.words().stream().filter(s-> s.equalsIgnoreCase(term) ).count();
        return count / (double) document.words().stream().count();
    }

    @Override
    public double idf(String term) {
        double count = 0;
        for(Document document: documents) {
            for(String word: document.words()) {
                if(term.equalsIgnoreCase(word)) {
                    count ++;
                    break;
                }
            }
        }
        if(count>0)
            return Math.log( documents.size() / count);
        else
            return 0;
    }

    @Override
    public double tdidf(Document document, String term) {
        return tf(document, term) * idf(term);
    }

    @Override
    public List<Document> getDocuments() {
        return documents;
    }
}
