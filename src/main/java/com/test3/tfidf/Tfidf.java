package com.test3.tfidf;

import java.util.List;

public interface Tfidf {

    double tf(Document document, String term);
    double idf(String term);
    double tdidf(Document document, String term);
    List<Document> getDocuments();

}
