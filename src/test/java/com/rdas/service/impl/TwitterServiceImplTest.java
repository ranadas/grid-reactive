package com.rdas.service.impl;

import com.rdas.model.TwitterResponse;
import com.rdas.service.TwitterService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.social.twitter.api.SearchParameters;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
//https://github.com/jreyes/spring-social-twitter-example/blob/master/src/main/java/org/springframework/social/quickstart/TwitterController.java
//https://github.com/spring-projects/spring-social-twitter/blob/master/spring-social-twitter/src/test/java/org/springframework/social/twitter/api/impl/SearchParametersTest.java
@EnableConfigurationProperties
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TwitterServiceImplTest {

    @Autowired
    private TwitterService twitterService;

    @Value("${spring.social.twitter.app-id}")
    private String appId;

    @Value("${spring.social.twitter.app-secret}")
    private String appSecret;


    @Test
    public void assertThatServiceIsValid() {
        Assert.assertNotNull(twitterService);
    }

    @Test
    public void assertThatServiceCalFatchTweets() throws UnsupportedEncodingException {
        String search = "pwittchen/ReactiveNetwork";
        String encodedString = URLEncoder.encode(search, StandardCharsets.UTF_8.toString());
        //List<TwitterResponse> tweets = twitterService.search(search);
        List<String> tweets = twitterService.search(search);
        log.info(tweets.toString());
        Assert.assertNotNull(search);
    }

    @Test
    public void brutForceRun() {
        TwitterTemplate twitterTemplate = new TwitterTemplate(appId, appSecret);
        int count = 5;

        SearchResults results = twitterTemplate.searchOperations().search(
                new SearchParameters("ReactiveNetwork")
                        .resultType(SearchParameters.ResultType.MIXED)
                        .includeEntities(false)
                        .count(count));

        List<Tweet> tweets = results.getTweets();

        tweets.parallelStream().forEach(t -> {
            System.out.println(t.getText());
        });
//        String forObject = twitter.restOperations().getForObject("https://api.twitter.com/1.1/search/tweets.json?q=cpwittchen/ReactiveNetwork&result_type=mixed&count=4", String.class);
//        System.out.println(forObject);
        System.out.println("stop");
    }
}