package com.rdas.service;

import com.rdas.model.RepositorySummary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ServiceAggregator {

    public RepositorySummary aggregate(){
        log.info("\n\nWIOOO HOO\n\n");
        return RepositorySummary.builder().build();
    }
}
