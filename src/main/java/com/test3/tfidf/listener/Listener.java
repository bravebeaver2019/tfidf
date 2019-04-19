package com.test3.tfidf.listener;

import com.test3.tfidf.Document;

/**
 * interface to be implemented if you want to be aware of new documents to be indexed.
 */
public interface Listener {

    void index(Document document);

}
