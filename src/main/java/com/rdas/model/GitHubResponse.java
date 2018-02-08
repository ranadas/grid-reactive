package com.rdas.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GitHubResponse {
    @JsonProperty("total_count")
    private Integer totalCount;

    @JsonProperty("incomplete_results")
    private Boolean incompleteResults;

    @JsonProperty("items")
    private List<RepositoryItem> repositoryItems;
}
