package com.rdas.service;


import com.rdas.model.GitHubResponse;

public interface GitHubService {
    GitHubResponse search(String searchString);
}
