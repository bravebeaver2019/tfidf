package com.test3.tfidf;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@Slf4j
public class TfdifSearchTest {

    @InjectMocks
    TfdifSearch search;

    @Mock
    Tfidf tfidf;

    Document d1 = new Document("d1", "a b c d e f g");
    Document d2 = new Document("d2", "b c d e f g h");
    Document d3 = new Document("d3", "b c d e f g h");
    Document d4 = new Document("d4", "b c d e f g h");

    @Test
    public void testLimit() {
        ArrayList<Document> documents = new ArrayList() {{
            add(d1); add(d2); add(d3); add(d4);
        }};
        when(tfidf.getDocuments()).thenReturn(new HashSet<>(documents));
        search.limit = 3;
        List<SearchRank> result = search.search("a b c");
        assertEquals(3, result.size());
        search.limit = 2;
        result = search.search("a b c");
        assertEquals(2, result.size());
    }

    @Test
    public void testAggregation() {
        ArrayList<Document> documents = new ArrayList() {{
            add(d1); add(d2); add(d3); add(d4);
        }};
        when(tfidf.getDocuments()).thenReturn(new HashSet<>(documents));
        when(tfidf.tdidf(d1, "a")).thenReturn(0.9);
        when(tfidf.tdidf(d1, "b")).thenReturn(0.9);
        when(tfidf.tdidf(d1, "c")).thenReturn(0.9);
        when(tfidf.tdidf(d2, "a")).thenReturn(1.0);
        when(tfidf.tdidf(d2, "b")).thenReturn(1.0);
        when(tfidf.tdidf(d2, "c")).thenReturn(1.0);
        search.limit = 3;
        List<SearchRank> result = search.search("a b c");
        double d1Frequency = result.stream().filter(s -> s.getDocument().getName().equals("d1")).findFirst().get().getFrequency();
        assertEquals(0.729, d1Frequency, 0.001);
        double d2Frequency = result.stream().filter(s -> s.getDocument().getName().equals("d2")).findFirst().get().getFrequency();
        assertEquals(1.0, d2Frequency, 0.1);
    }
}
