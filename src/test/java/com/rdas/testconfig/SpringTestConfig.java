package com.rdas.testconfig;

import com.rdas.model.GitHubResponse;
import com.rdas.service.GitHubService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringTestConfig {

    @Bean(name = "basicGHService")
    public GitHubService basicGHService() {
        return searchString -> new GitHubResponse();
    }
}