package com.test3.tfidf.watcher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
/**
 * Runs a file watcher daemon inside spring context.
 */
public class FileWatcher {

    @Autowired
    ObjectFactory<FileWatcherRunnable> threadFactory;

    @PostConstruct
    public void watch() {
        new Thread(threadFactory.getObject()).start();
    }
}
