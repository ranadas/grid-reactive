package com.rdas.service.impl;

import com.rdas.model.*;
import com.rdas.service.Aggregator;
import com.rdas.service.GitHubService;
import com.rdas.service.TwitterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Aggregates GitHub and Twitter.
 */
@Slf4j
@Service
public class ServiceAggregator implements Aggregator {

    private GitHubService gitHubService;
    private TwitterService twitterService;

    @Autowired
    public ServiceAggregator(GitHubService gitHubService, TwitterService twitterService) {
        this.gitHubService = gitHubService;
        this.twitterService = twitterService;
    }

    public Optional<List<RepositorySummary>> aggregate(String searchTerm) throws IOException {
        Optional<GitHubResponse> gitHubResponse = gitHubService.search(searchTerm);
        if (!gitHubResponse.isPresent()) {
            return Optional.empty();
        }
        List<RepositorySummary> collect = gitHubResponse.get().getRepositoryItems()
                .stream()
                .map(item -> itemConverter.apply(item))
                .collect(Collectors.toList());
        log.debug(collect.toString());
        return Optional.of(collect);
    }

    Function<RepositoryItem, Optional<List<TwitterResponse>>> getTweets = item -> twitterService.searchResponse(item.getHtmlUrl());

    Function<RepositoryItem, RepositorySummary> itemConverter = item ->
            RepositorySummary.builder()
                    .repositoryName(item.getName())
                    .repositoryDescription(item.getDescription())
                    .repositoryPublicUrl(item.getHtmlUrl())
                    .tweets(getTweets.apply(item))
                    .build();
}
