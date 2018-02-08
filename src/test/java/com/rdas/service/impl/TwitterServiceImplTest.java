package com.rdas.service.impl;

import com.rdas.model.TwitterResponse;
import com.rdas.service.TwitterService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.social.twitter.api.SearchParameters;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
//https://github.com/jreyes/spring-social-twitter-example/blob/master/src/main/java/org/springframework/social/quickstart/TwitterController.java
//https://github.com/spring-projects/spring-social-twitter/blob/master/spring-social-twitter/src/test/java/org/springframework/social/twitter/api/impl/SearchParametersTest.java
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


//    @Autowired
//    private TwitterTemplate twitterTemplate;
    @Test
    public void brutForceRun() {
        TwitterTemplate twitterTemplate = new TwitterTemplate("aHSBLM2sUk9qN826xP5btJCyj", "5fJJfLu1HajaQ2ybmlBlE6DsGY5e1FMO7CGZLBsa8uKVMkmEuU");
        int count = 5;

        SearchResults results = twitterTemplate.searchOperations().search(
                new SearchParameters("github.com%2Fcomponent%2Freactive")
                        .resultType(SearchParameters.ResultType.RECENT)
                        .count(count));

        List<Tweet> tweets = results.getTweets();

        tweets.parallelStream().forEach(t -> {
            System.out.println(t.getText());
        });
        System.out.println("stop");
    }


}