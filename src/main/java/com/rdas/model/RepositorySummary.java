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
    private String reposityName;
    private String reposityDescription;
    private String reposityPublicUrl;
    private Optional<List<TwitterResponse>> tweets;

    public List<TwitterResponse> getTweets() {
        return tweets.get();
    }
}