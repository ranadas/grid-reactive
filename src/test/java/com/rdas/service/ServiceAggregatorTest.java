package com.rdas.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceAggregatorTest {
    @Autowired
    private Aggregator aggregator;


    @Test
    public void assertThatServicesAreInjected() {
        assertNotNull(aggregator);
    }

    @Test
    public void assertThatAggregatorTakesEmptyString() throws IOException{
        assertNotNull(aggregator.aggregate(""));
    }
}