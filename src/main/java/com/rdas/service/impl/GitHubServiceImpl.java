package com.rdas.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rdas.model.GitHubResponse;
import com.rdas.service.GitHubService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class GitHubServiceImpl implements GitHubService {
    private ObjectMapper objectMapper;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private String externalServiceUrl;

    public GitHubServiceImpl(@Qualifier("localMapper") @Autowired ObjectMapper localMapper, @Value("${external_url}") String serviceUrl) {
        this.objectMapper = localMapper;
        this.externalServiceUrl = serviceUrl;
    }

    @Override
    public GitHubResponse search(String searchString) throws IOException {
        //TODO check empty string
        HttpUrl.Builder urlBuilder = HttpUrl.parse(externalServiceUrl).newBuilder();
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
            e.printStackTrace();
        }
        String jsonResponse = responses.body().string();

        GitHubResponse gitHubResponse = objectMapper.readValue(jsonResponse, GitHubResponse.class);
        System.out.println(objectMapper.writeValueAsString(gitHubResponse));
        return gitHubResponse;
    }
}