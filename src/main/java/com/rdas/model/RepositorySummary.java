package com.rdas.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RepositorySummary {
    private String reposityName;
    private String reposityDescription;
    private String reposityPublicUrl;
    private List<TwitterResponse> tweets;
}