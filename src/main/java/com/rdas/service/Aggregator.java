package com.rdas.service;

import com.rdas.model.RepositorySummary;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface Aggregator {
    Optional<List<RepositorySummary>> aggregate(String searchTerm) throws IOException;
}
