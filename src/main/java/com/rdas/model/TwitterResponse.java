package com.rdas.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.social.twitter.api.HashTagEntity;
import org.springframework.social.twitter.api.MentionEntity;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.TwitterProfile;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Builder
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TwitterResponse {
    private String user;
    private String text;
    private String lang;
    private LocalDateTime dateTime;

    public static TwitterResponse ofTweet(Tweet tweet) {
        TwitterResponse lightTweet = TwitterResponse.builder().build();
        lightTweet.setText(tweet.getText());

        Date createdAt = tweet.getCreatedAt();
        if (createdAt != null) {
            //lightTweet.dateTime = LocalDateTime.ofInstant(createdAt.toInstant(), ZoneId.systemDefault());
        }
        TwitterProfile twitterUser = tweet.getUser();
        if (twitterUser != null) {
            lightTweet.user = twitterUser.getName();
        }
        lightTweet.lang = tweet.getLanguageCode();

//        tweet.getEntities().getMentions().forEach((MentionEntity m) -> System.out.println(m.getScreenName() + ", "  + m.getName()));
//        tweet.getEntities().getHashTags().forEach((HashTagEntity t) -> System.out.println(t.getText()));
        return lightTweet;

    }

}
