package com.rdas.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.TwitterProfile;

@Builder
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TwitterResponse {
    private String user;
    private String text;
    private String lang;

    public static TwitterResponse ofTweet(Tweet tweet) {
        TwitterProfile twitterUser = tweet.getUser();
        String user = twitterUser != null ? twitterUser.getName() : "na";

        return TwitterResponse.builder()
                .text(tweet.getText())
                .lang(tweet.getLanguageCode())
                .user(user)
                .build();
    }
}
