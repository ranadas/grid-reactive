package com.rdas.service;

import com.rdas.model.TwitterResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TwitterServiceTest {

    @Autowired
    private TwitterService twitterService;

    @Test
    public void assertThatResponseNotNullForAmbiguousSearch() throws IOException {
        assertNotNull(twitterService.searchResponse("some searchString"));
    }

    @Test
    public void assertThatResponseNotNullForNullSearch() throws IOException {
        Optional<List<TwitterResponse>> twitterResponses = twitterService.searchResponse("");
        assertNotNull(twitterResponses);
        assertFalse(twitterResponses.isPresent());
    }

    @Test
    public void assertThatResponseNotNullForSearch() throws IOException {
        assertTrue(twitterService.searchResponse("Spring").isPresent());
    }
}