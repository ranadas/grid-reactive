package com.rdas.service;

import com.rdas.model.RepositorySummary;

import java.io.IOException;
import java.util.List;

public interface Aggregator {
    List<RepositorySummary> aggregate(String searchTerm) throws IOException;
}
