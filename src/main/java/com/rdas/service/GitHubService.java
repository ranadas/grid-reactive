package com.rdas.service;


import com.rdas.model.GitHubResponse;

import java.io.IOException;

public interface GitHubService {
    GitHubResponse search(String searchString) throws IOException;
}
