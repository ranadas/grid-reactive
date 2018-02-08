package com.rdas.service.impl;

import com.rdas.model.TwitterResponse;
import com.rdas.service.TwitterService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TwitterServiceImplTest {

    @Autowired
    private TwitterService twitterService;

    @Test
    public void assertThatServiceIsValid() {
        Assert.assertNotNull(twitterService);
    }

    @Test
    public void assertThatServiceCalFatchTweets() {
        List<TwitterResponse> search = twitterService.search("github.com/component/reactive");
        log.info(search.toString());
        Assert.assertNotNull(search);
    }


}