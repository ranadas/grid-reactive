package com.rdas.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class TwitterSearchResponses {
    List<TwitterSearchResult> twitterSearchResultList;
}
