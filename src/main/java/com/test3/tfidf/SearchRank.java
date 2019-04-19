package com.test3.tfidf;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public
class SearchRank {
    double frequency;
    String term;
    Document document;
    public String toString() {
        return "term: [" + term + "] frequency: " + String.format("%.2f",frequency) + " document: " + document.getName();
    }
}
