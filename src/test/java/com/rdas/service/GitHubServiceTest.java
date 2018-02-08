package com.rdas.service;

import com.rdas.testconfig.SpringTestConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes={SpringTestConfig.class})
public class GitHubServiceTest {

    @Qualifier("basicGHService")
    @Autowired
    private GitHubService basicGHService;

    @Test
    public void assertThatServiceReturnsNotNullResponse() throws IOException {
        Assert.assertNotNull(basicGHService.search("some searchString"));
    }

    @Test
    public void assertThatSearchIsNotNullForNllString() throws IOException {
        Assert.assertNotNull(basicGHService.search(""));
    }
}