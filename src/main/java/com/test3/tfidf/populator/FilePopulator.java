package com.test3.tfidf.populator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;
import java.util.UUID;

@Component
@Slf4j
public class FilePopulator {

    @Scheduled(fixedDelayString = "#{new Double((T(java.lang.Math).random() + 1) * 5000).intValue()}")
    public void createNewRandomFile() {
        try {
            log.info("creating new random file");
            StringBuilder builder = new StringBuilder(1024);
            for (int i=1; i<=1024; i++) {
                // every randome chars insert a space to make random length words
                if(i % ((int) ((Math.random() * 20)) + 5) == 0) {
                    builder.append(' ');
                } else {
                    builder.append((char) (97 + (int) (new Random().nextFloat() * (122 - 97 + 1))));
                }
            }
            Files.write(new File("/tmp/"+UUID.randomUUID()).toPath(), builder.toString().getBytes());
            log.info("random file created");
        } catch (IOException e) {
            log.warn("could not create random file");
        }
    }

    public static void main(String[] args) {
        System.err.println(((int)((Math.random() *20)) + 5));
    }
}
