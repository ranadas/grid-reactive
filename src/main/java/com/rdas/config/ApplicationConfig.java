package com.rdas.config;

import com.rdas.model.GitHubResponse;
import com.rdas.service.GitHubService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public GitHubService gitHubService () {
        return new GitHubService() {
            @Override
            public GitHubResponse search(String searchString) {
                return null;
            }
        };
    }
}
