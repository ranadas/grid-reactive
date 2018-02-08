package com.rdas.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;
import java.util.Optional;

@Builder
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RepositorySummary {
    private String repositoryName;
    private String repositoryDescription;
    private String repositoryPublicUrl;
    private Optional<List<TwitterResponse>> tweets;

    public List<TwitterResponse> getTweets() {
        return tweets.get();
    }
}