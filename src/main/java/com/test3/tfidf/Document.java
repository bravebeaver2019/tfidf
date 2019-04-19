package com.test3.tfidf;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Data
@AllArgsConstructor
public class Document {

    private String name;
    private String content;

    public List<String> words(String delimiters) {
        // todo: test this method
        List<String> strings = new ArrayList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(content, delimiters);
        while (stringTokenizer.hasMoreTokens()) {
            strings.add(stringTokenizer.nextToken());
        }
        return strings;
    }
}

