package com.rdas.service.impl;

import com.rdas.model.TwitterResponse;
import com.rdas.service.TwitterService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.social.twitter.api.SearchParameters;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * this only returns Recent tweets, if we need another order, we will have to have a different component,.
 */
@Slf4j
@Service
public class RecentTweetsService implements TwitterService {

    private Twitter twitter;
    private Integer rateLimit;

    public RecentTweetsService(@Autowired Twitter twitter, @Value("${spring.social.twitter.ratelimit:10}") Integer rateLimit) {
        this.twitter = twitter;
        this.rateLimit = rateLimit;
    }

    @Cacheable("searches")
    @Override
    public Optional<List<TwitterResponse>> searchResponse(@NotNull String searchString) {
        if (StringUtils.isEmpty(searchString)) {
            return Optional.empty();
        }

        List<TwitterResponse> collect = twitter.searchOperations().search(
                new SearchParameters(searchString)
                        .resultType(SearchParameters.ResultType.RECENT)
                        .count(rateLimit)
                        .includeEntities(false))
                .getTweets().stream()
                .map(TwitterResponse::ofTweet)
                .collect(Collectors.toList());
        return Optional.ofNullable(collect);
    }
}

