package com.rdas.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rdas.model.TwitterResponse;
import com.rdas.service.TwitterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.social.twitter.api.SearchParameters;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TwitterServiceImpl implements TwitterService {

    private Twitter twitter;
    private ObjectMapper objectMapper;

    public TwitterServiceImpl(@Qualifier("localMapper") @Autowired ObjectMapper localMapper, @Autowired Twitter twitter) {
        this.twitter = twitter;
        this.objectMapper = localMapper;
    }

    @Autowired
    private TwitterClient twitterClient;
//    @Cacheable("searches")
//    @Override
//    public List<String> search(String keyword){
//        return twitterClient.searchTweets(keyword)
//                .getTweets().stream()
//                .map(Tweet::getText)
//                .collect(Collectors.toList());
//    }

    @Cacheable("searches")
    @Override
    public List<TwitterResponse> searchResponse(String searchString) {
//        List<TwitterResponse> react = search(SearchParameters.ResultType.MIXED.toString(), Arrays.asList(searchString));
//        //List<LightTweet> react = search(SearchParameters.ResultType.RECENT.toString(), Arrays.asList("Java"));
//        log.info(react.toString());
//        return react;
        return twitterClient.searchTweets(searchString)
                .getTweets().stream()
                .map(TwitterResponse::ofTweet)
                .collect(Collectors.toList());
    }
//
//    public List<TwitterResponse> search(String searchType, List<String> keywords) {
//
//        List<SearchParameters> searches = keywords.stream()
//                .map(keyword -> createSearchParam(searchType, keyword))
//                .collect(Collectors.toList());
//
//        SearchParameters searchParam = createSearchParam(searchType, "ReactiveNetwork");
//        List<TwitterResponse> collect = twitter.searchOperations()
//                .search(searchParam)
//                .getTweets().stream().map(TwitterResponse::ofTweet)
//                .collect(Collectors.toList());
//        /*
//        List<TwitterResponse> results = searches.stream()
//                .map(params -> twitter.searchOperations().search(params))
//                .flatMap(searchResults -> searchResults.getTweets().stream())
//                .map(TwitterResponse::ofTweet)
//                .collect(Collectors.toList());
//        */
//        return collect;
//    }
//
//    private SearchParameters createSearchParam(String searchType, String keyword) {
//        SearchParameters.ResultType resultType = getResultType(searchType);
//        SearchParameters searchParameters = new SearchParameters(keyword);
//        searchParameters.resultType(resultType);
//        searchParameters.count(5);
//        return searchParameters;
//    }
//
//    private SearchParameters.ResultType getResultType(String searchType) {
//        for (SearchParameters.ResultType knownType : SearchParameters.ResultType.values()) {
//            if (knownType.name().equalsIgnoreCase(searchType)) {
//                return knownType;
//            }
//        }
//        return SearchParameters.ResultType.RECENT;
//    }
}

