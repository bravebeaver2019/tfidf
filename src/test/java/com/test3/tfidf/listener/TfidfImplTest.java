package com.test3.tfidf.listener;

import com.test3.tfidf.Document;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

@Slf4j
public class TfidfImplTest {

    public static final double DELTA = 0.000001;
    Document document1, document2, document3, document4, document5;
    TfidfImpl listener;

    @Before
    public void init() {
        document1 = new Document("doc1", "how are you doing today");
        document2 = new Document("doc2", "how are we writing codehow are we writing codehow are we writing code");
        document3 = new Document("doc3", "how many people are us HOW");
        document4 = new Document("doc4", "how");
        document5 = new Document("doc5", "how how");
        listener = new TfidfImpl();
        listener.documents = new HashSet<>();
        listener.documents.addAll(Arrays.asList(document1, document2, document3, document4, document5));
    }

    @Test
    public void testTFFrequent1() {
        assertEquals(".how." ,
                0.2, listener.tf(document1, "how"), DELTA);
    }

    @Test
    public void testTFFrequent2() {
        assertEquals(".codehow." ,
                0.15384615384615385, listener.tf(document2, "codehow"), DELTA);
    }

    @Test
    public void testTFFrequent3() {
        assertEquals(".how." ,
                0.3333333333, listener.tf(document3, "how"), DELTA);
    }

    @Test
    public void testTFNotFound() {
        assertEquals(".wreck." ,
                0, listener.tf(document3, "wreck"), DELTA);
    }

    @Test
    public void testTFRare1() {
        assertEquals(".how." ,
                1.0, listener.tf(document4, "how"), DELTA);
    }

    @Test
    public void testTFRare2() {
        assertEquals(".how." ,
                1.0, listener.tf(document5, "how"), DELTA);
    }

    @Test
    public void testIDFVeryCommonTerm() {
        assertEquals(".how. " ,
                0.0, listener.idf("how"), DELTA);
    }

    @Test
    public void testIDFVeryRareTerm() {
        assertEquals(".writing." ,
                1.6094379124341003, listener.idf("writing"), DELTA);
    }

    @Test
    public void testIDFSomehowCommonTerm() {
        assertEquals(".are." ,
                0.5108256237659907, listener.idf("are"), DELTA);
    }

    @Test
    public void testTDIDFVeryRareTerm() {
        assertEquals(".people." ,
                0.26823965207235, listener.tdidf(document3, "people"), DELTA);
    }

    @Test
    public void testTDIDFVeryRareTerm2() {
        assertEquals(".writing." ,
                0.37140874902325394, listener.tdidf(document2, "writing"), DELTA);
    }

    @Test
    public void testTDIDFSomehowRareTerm() {
        assertEquals(".are." ,
                0.11788283625369017, listener.tdidf(document2, "are"), DELTA);
    }

    @Test
    public void testTDIDFVeryCommonTerm() {
        assertEquals(".how." ,
                0.0, listener.tdidf(document1, "how"), DELTA);
    }
}
