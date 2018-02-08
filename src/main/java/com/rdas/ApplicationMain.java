package com.rdas;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ApplicationMain implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationMain.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
//        List<RepositorySummary> aggregate = serviceAggregator.aggregate();
        //log.info("\n{}\n\n", objectMapper.writeValueAsString(aggregate));
    }
}
