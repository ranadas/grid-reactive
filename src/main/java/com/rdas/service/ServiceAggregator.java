package com.rdas.service;

import com.rdas.model.GitHubResponse;
import com.rdas.model.Item;
import com.rdas.model.RepositorySummary;
import com.rdas.model.TwitterResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ServiceAggregator {

    private GitHubService gitHubService;
    private TwitterService twitterService;

    public ServiceAggregator(@Autowired GitHubService gitHubService, @Autowired TwitterService twitterService) {
        this.gitHubService = gitHubService;
        this.twitterService = twitterService;
    }

    public List<RepositorySummary> aggregate() throws IOException {
        GitHubResponse gitHubResponse = gitHubService.search("Reactive");
        return gitHubResponse.getItems()
                .stream()
                .limit(2)
                .map(item -> itemConverter.apply(item))
                .collect(Collectors.toList());
    }

    Function<Item, List<TwitterResponse>> getTweets = item -> twitterService.search(item.getFullName());

    Function<Item, RepositorySummary> itemConverter = item ->
            RepositorySummary.builder()
                    .name(item.getName())
                    .description(item.getDescription())
                    .publicUrl(item.getHtmlUrl())
                    .tweets(getTweets.apply(item))
                    .build();
}
