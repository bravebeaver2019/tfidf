package com.test3.tfidf.notifier;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

@Component
@Slf4j
public class WovelsFrequencyNotifier {

    @Autowired
    @Qualifier("SimpleCount")
    Map<String,Integer> simpleCount;

    @Scheduled(fixedDelay = 1_000)
    public void notifyResult() {
        log.info("----------------------- Wovels frequency -----------------------");
        log.info(result("a"));
        log.info(result("e"));
        log.info(result("i"));
        log.info(result("o"));
        log.info(result("u"));
        log.info("----------------------- Wovels frequency -----------------------");
    }

    String result(String k) {
        Integer r = simpleCount.get(k);
        if(r==null) r = 0;
        return k + ": " + r;
    }

}
