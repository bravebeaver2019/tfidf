package com.test3.tfidf.watcher;

import com.test3.tfidf.Document;
import com.test3.tfidf.listener.Listener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

@Component
@Scope("prototype")
@Slf4j
public class FileWatcherRunnable implements Runnable {

    @Autowired
    Listener documentListener;

    @Override
    public void run() {
        log.info("Watcher daemon starting");
        try {
            WatchService watcher = FileSystems.getDefault().newWatchService();
            Path dir = new File("/tmp").toPath();
            WatchKey key = dir.register(watcher, ENTRY_CREATE);
            while (true) {
                log.info("Watcher daemon running...");
                try {
                    key = watcher.take();
                    for (WatchEvent<?> event: key.pollEvents()) {
                        WatchEvent<Path> ev = (WatchEvent<Path>)event;
                        Path filename = ev.context();
                        Path child = dir.resolve(filename);
                        log.info("new file found " + child);
                        documentListener.index(new Document(filename.toString(), new String(Files.readAllBytes(child))));
                    }
                    boolean valid = key.reset();
                    if (!valid) {
                        break;
                    }
                } catch (InterruptedException x) {
                    return;
                } catch (Throwable t) { // ignore event reading errors
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Watcher daemon unexpectedly stopped due to: ", e);
        }
    }
}
