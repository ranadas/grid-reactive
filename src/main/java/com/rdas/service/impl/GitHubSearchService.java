package com.rdas.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rdas.model.GitHubResponse;
import com.rdas.service.GitHubService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
public class GitHubSearchService implements GitHubService {
    private ObjectMapper objectMapper;

    private String githubSearchEndpoint;

    public GitHubSearchService(@Qualifier("localMapper") @Autowired ObjectMapper localMapper, @Value("${github_repository_search_endpoint}") String serviceUrl) {
        this.objectMapper = localMapper;
        this.githubSearchEndpoint = serviceUrl;
    }

    @Override
    public Optional<GitHubResponse> search(String searchString) throws IOException {
        if (StringUtils.isEmpty(searchString)) {
            return Optional.empty();
        }

        HttpUrl.Builder urlBuilder = HttpUrl.parse(githubSearchEndpoint).newBuilder();
        urlBuilder.addQueryParameter("q", searchString);
        String url = urlBuilder.build().toString();

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response responses = null;

        try {
            responses = client.newCall(request).execute();
        } catch (IOException e) {
            log.error(e.getLocalizedMessage(), e);
            throw e;
        }
        String jsonResponse = responses.body().string();

        GitHubResponse gitHubResponse = objectMapper.readValue(jsonResponse, GitHubResponse.class);
        log.debug("Full gitHub response : {} ", objectMapper.writeValueAsString(gitHubResponse));
        return Optional.of(gitHubResponse);
    }
}
