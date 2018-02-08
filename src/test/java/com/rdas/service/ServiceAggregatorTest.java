package com.rdas.service;

import com.rdas.testconfig.SpringTestConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SpringTestConfig.class})
public class ServiceAggregatorTest {
    @Qualifier("basicGHService")
    @Autowired
    private GitHubService gitHubService;


    @Test
    public void assertThatServicesAreInjected() {
        Assert.assertNotNull(gitHubService);
    }
}