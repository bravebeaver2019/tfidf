package com.test3.tfidf;

import java.util.Set;

/**
 * Main TFIDF algorithm object.
 */
public interface Tfidf {

    double tf(Document document, String term);
    double idf(String term);
    double tdidf(Document document, String term);
    Set<Document> getDocuments();

}
