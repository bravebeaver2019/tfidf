package com.test3.tfidf;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
/**
 * displays a search result for a term in a doc.
 */
public class SearchRank {
    double frequency;
    String term;
    Document document;
    public String toString() {
        return "term: [" + term + "] frequency: " + String.format("%.6f",frequency) + " document: " + document.getName();
    }
}
