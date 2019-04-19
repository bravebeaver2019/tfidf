package com.test3.tfidf;

import com.test3.tfidf.populator.FilePopulator;
import com.test3.tfidf.watcher.FileWatcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
@ComponentScan(basePackages = {"com.test3.tfidf"})
@EnableScheduling
@Slf4j
public class TfidfApplication {

	public static void main(String[] args) {
		SpringApplication.run(TfidfApplication.class, args);
	}

	@Bean
	public ApplicationRunner commandLineRunner() {
		return args -> {};
	}
}
