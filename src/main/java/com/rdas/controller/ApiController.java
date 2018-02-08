package com.rdas.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rdas.model.RepositorySummary;
import com.rdas.model.TwitterSearchResponses;
import com.rdas.service.ServiceAggregator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
public class ApiController {

    private ServiceAggregator serviceAggregator;
    private ObjectMapper objectMapper;

    @Autowired
    public ApiController(ServiceAggregator serviceAggregator, ObjectMapper objectMapper) {
        this.serviceAggregator = serviceAggregator;
        this.objectMapper = objectMapper;
    }

    @GetMapping(path = "/tweets")
    public ResponseEntity<?> check(@RequestParam(name = "search", defaultValue = "Reactive") String searchTerm) throws IOException {

        List<RepositorySummary> aggregate = serviceAggregator.aggregate(searchTerm);
//        TwitterSearchResponses aggregate = serviceAggregator.aggregate(searchTerm);

        //this is here so that the response is formatted, not necessary.
        String formattedJson = objectMapper.writeValueAsString(aggregate);

        return new ResponseEntity<>(formattedJson, HttpStatus.OK);
    }
}
