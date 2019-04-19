package com.test3.tfidf.populator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
@Slf4j
/**
 * If enabled, populates filesystem with semirandom documents.
 */
public class FilePopulator {

    @Value("${tfidf.populator.enabled}")
    Boolean populatorEnabled;
    @Value("${tfidf.search.dir}")
    String path;

    List<String> commonWords = new ArrayList() {{
        add("at");
        add("on");
        add("is");
        add("if");
        add("or");
        add("yes");
        add("of");
    }};

    @Scheduled(fixedDelay = 30_000)
    public void createKnownFile() {
        if(populatorEnabled) {
            log.info("creating new known terms file");
            StringBuilder builder = new StringBuilder(1024);
            builder.append("abc some common terms is are at on where is the on at there abc abc abc");
            try {
                Files.write(new File(path + "/" + UUID.randomUUID()).toPath(), builder.toString().getBytes());
            } catch (IOException e) {
                throw new RuntimeException("", e);
            }
        }
    }

    @Scheduled(fixedDelay = 30_000)
    public void createKnownFile2() {
        if(populatorEnabled) {
            log.info("creating new known common terms file");
            StringBuilder builder = new StringBuilder(1024);
            for(int i=0; i<10; i++) { builder.append("at "); }
            try {
                Files.write(new File(path + "/" + UUID.randomUUID()).toPath(), builder.toString().getBytes());
            } catch (IOException e) {
                throw new RuntimeException("", e);
            }
        }
    }

    @Scheduled(fixedDelayString = "#{new Double((T(java.lang.Math).random() + 1) * 5000).intValue()}")
    public void createNewRandomFile() {
        if(populatorEnabled) {
            try {
                log.info("creating new random file");
                StringBuilder builder = new StringBuilder(1024);
                if(Math.random()>0.7) builder.append(" abc "); // some docs have abc term
                for (int i=1; i<=2048; i++) {
                    // every some random chars insert a space to make random length words
                    // add also a common word
                    if(i % ((int) ((Math.random() * 10)) + 5) == 0) {
                        builder.append(' ');
                    } else {
                        builder.append((char) (97 + (int) (new Random().nextFloat() * (122 - 97 + 1))));
                    }
                }
                Files.write(new File(path + "/" + UUID.randomUUID()).toPath(), builder.toString().getBytes());
                log.info("random file created");
            } catch (IOException e) {
                log.warn("could not create random file");
            }
        }
    }
}
