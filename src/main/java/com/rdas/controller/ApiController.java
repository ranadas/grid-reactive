package com.rdas.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rdas.model.RepositorySummary;
import com.rdas.service.Aggregator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class ApiController {

    private Aggregator serviceAggregator;
    private ObjectMapper objectMapper;

    @Autowired
    public ApiController(Aggregator serviceAggregator, ObjectMapper objectMapper) {
        this.serviceAggregator = serviceAggregator;
        this.objectMapper = objectMapper;
    }

    @GetMapping(path = "/tweets",produces = "application/json")
    public ResponseEntity<?> check(@RequestParam(name = "search", defaultValue = "Reactive") String searchTerm) throws IOException {
        Optional<List<RepositorySummary>> aggregate = serviceAggregator.aggregate(searchTerm);
        if (aggregate.isPresent()) {
            //sending a formatted json string back instead of the object itself.
            String formattedJson = objectMapper.writeValueAsString(aggregate.get());
            return new ResponseEntity<>(formattedJson, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Empty Search", HttpStatus.OK);
        }
    }
}
