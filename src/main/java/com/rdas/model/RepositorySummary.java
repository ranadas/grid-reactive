package com.rdas.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RepositorySummary {
    private String name;
    private String description;
    private String publicUrl;
//    private List<LightTweet> tweetList;
}