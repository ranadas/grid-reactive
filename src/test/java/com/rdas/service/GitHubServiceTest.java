package com.rdas.service;

import com.rdas.model.GitHubResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Optional;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GitHubServiceTest {

    @Autowired
    private GitHubService basicGHService;

    @Test
    public void assertThatResponseNotNullForAmbiguousSearch() throws IOException {
        assertNotNull(basicGHService.search("some searchString"));
    }

    @Test
    public void assertThatResponseNotNullForNullSearch() throws IOException {
        Optional<GitHubResponse> optionalSerarch = basicGHService.search("");
        assertNotNull(optionalSerarch);
        assertFalse(optionalSerarch.isPresent());
    }

    @Test
    public void assertThatResponseNotNullForSearch() throws IOException {
        Optional<GitHubResponse> optionalSerarch = basicGHService.search("Spring");
        assertNotNull(optionalSerarch);
        assertTrue(optionalSerarch.isPresent());
    }
}