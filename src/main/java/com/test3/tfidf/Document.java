package com.test3.tfidf;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
public class Document {

    private String name;
    private String content;

    public List<String> words() {
        return Arrays.asList(content.split(" "));
    }
}
