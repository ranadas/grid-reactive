package com.rdas.service;

import com.rdas.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ServiceAggregator {

    private GitHubService gitHubService;
    private TwitterService twitterService;

    @Autowired
    public ServiceAggregator(GitHubService gitHubService, TwitterService twitterService) {
        this.gitHubService = gitHubService;
        this.twitterService = twitterService;
    }

    public List<RepositorySummary> aggregate(String searchTerm) throws IOException {
//    public TwitterSearchResponses aggregate(String searchTerm) throws IOException {
        GitHubResponse gitHubResponse = gitHubService.search(searchTerm);

        List<RepositorySummary> collect = gitHubResponse.getRepositoryItems()
                .stream()
//                .limit(2)
                .map(item -> itemConverter.apply(item))
                .collect(Collectors.toList());
        log.info(collect.toString());
        return collect;
//        TwitterSearchResponses t = TwitterSearchResponses.builder()
//                //.twitterSearchResultList(gitHubService.searchReactiveRepositories().parallelStream()
//                .twitterSearchResultList(gitHubResponse.getRepositoryItems().stream()
//                        .map(item -> TwitterSearchResult.builder()
//                                .query(item.getHtmlUrl())
//                                //.tweets(twitterService.getTweets(item.getHtmlURL()))
//                                .tweets(twitterService.search(item.getHtmlUrl()))
//                                .build())
//                        .filter(f -> !f.getTweets().isEmpty())
//                        .collect(Collectors.toList())
//                )
//                .build();
//        return t;

    }

    //Function<Item, List<TwitterResponse>> getTweets = item -> twitterService.search(item.getFullName());
    Function<RepositoryItem, List<TwitterResponse>> getTweets = item -> twitterService.searchResponse(item.getHtmlUrl());
//
    Function<RepositoryItem, RepositorySummary> itemConverter = item ->
            RepositorySummary.builder()
                    .name(item.getName())
                    .description(item.getDescription())
                    .publicUrl(item.getHtmlUrl())
                    .tweets(getTweets.apply(item))
                    .build();
}
