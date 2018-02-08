package com.rdas.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@ToString
public class RepositoryItem {
    private String id;
    private String name;
    @JsonProperty("full_name")
    private String fullName;
    private String description;
    private String url;
    @JsonProperty("html_url")
    private String htmlUrl;
    @JsonProperty("git_url")
    private String gitUrl;
    private String language;
}
