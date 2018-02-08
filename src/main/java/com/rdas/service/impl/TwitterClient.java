package com.rdas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.SearchParameters;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Component;

@Component
public class TwitterClient {
    @Autowired
    private Twitter twitter;

    public SearchResults searchTweets(String query) {
        return twitter.searchOperations().search(
                new SearchParameters(query)
                        .resultType(SearchParameters.ResultType.RECENT)
                        .count(10)
                        .includeEntities(false));
    }
}
