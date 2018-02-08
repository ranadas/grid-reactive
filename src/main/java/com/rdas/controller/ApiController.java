package com.rdas.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rdas.model.RepositorySummary;
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

    @Autowired
    private ServiceAggregator serviceAggregator;

    @Autowired
    private ObjectMapper objectMapper;


    @GetMapping(path = "/tweets", produces = "application/json")
    public ResponseEntity<?> check(@RequestParam(required = true, name = "search") String searchTerm) throws IOException {
        List<RepositorySummary> aggregate = serviceAggregator.aggregate();

        String formattedJson = objectMapper.writeValueAsString(aggregate);

        return new ResponseEntity<Object>(formattedJson, HttpStatus.OK);
    }
}
