package com.rdas;

import com.rdas.service.ServiceAggregator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ApplicationMain implements CommandLineRunner {

    @Autowired
    private ServiceAggregator serviceAggregator;

    public static void main(String[] args) {
        SpringApplication.run(ApplicationMain.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        serviceAggregator.aggregate();
    }
}
