package com.rdas.controller;

import com.rdas.model.GitHubResponse;
import com.rdas.model.RepositoryItem;
import com.rdas.model.TwitterResponse;
import com.rdas.service.GitHubService;
import com.rdas.service.TwitterService;
import com.rdas.testconfig.SpringTestConfig;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//https://spring.io/blog/2016/04/15/testing-improvements-in-spring-boot-1-4
//https://springframework.guru/mocking-unit-tests-mockito/
@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = SpringTestConfig.class)
@AutoConfigureMockMvc
public class ApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

//    @MockBean
//    private TwitterService twitterService;
//
//    @MockBean
//    private GitHubService gitHubService;
//
//    @Before
//    public void setup() throws IOException {
//        given(twitterService.searchResponse("Reactive"))
//                .willReturn(reactResponse());
//        given(gitHubService.search("Reactive"))
//                .willReturn(githubResponse());
//    }

    @Test
    public void assertThatBeansAreValid() throws Exception {
        assertNotNull(mockMvc);
        assertNotNull(wac.getBean("apiController"));
    }

    @Test
    public void assertThatDefaultGetReturnsReactSearch() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/tweets")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();

        assertThat(content, containsString("reactive"));
    }

    @Ignore
    public void assertThatScalaGetReturnsScalaTweets() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/tweets")
                .contentType(MediaType.APPLICATION_JSON)
                .param("search", "Scala"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();

        assertThat(content, containsString("scala"));
    }

//    private Optional<List<TwitterResponse>> reactResponse() {
//        List<TwitterResponse> twitterResponses = new ArrayList<>();
//        twitterResponses.add(
//                TwitterResponse.builder()
//                        .user("adam")
//                        .lang("en")
//                        .text("reactive").build());
//
//        return Optional.of(twitterResponses);
//    }
//
//    private  Optional<GitHubResponse> githubResponse() {
//        GitHubResponse gitHubResponse = new GitHubResponse();
//        RepositoryItem repositoryItem = new RepositoryItem();
//        repositoryItem.setHtmlUrl("github.com");
//        repositoryItem.setName("RxRealm");
//        repositoryItem.setFullName("reactive/RxRealm");
//        List<RepositoryItem> repositoryItems = new ArrayList<>();
//        gitHubResponse.setRepositoryItems(repositoryItems);
//        return Optional.of(gitHubResponse);
//    }
}