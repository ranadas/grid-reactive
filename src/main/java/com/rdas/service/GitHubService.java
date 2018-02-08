package com.rdas.service;


import com.rdas.model.GitHubResponse;

import java.io.IOException;
import java.util.Optional;

public interface GitHubService {
    Optional<GitHubResponse> search(String searchString) throws IOException;
}
