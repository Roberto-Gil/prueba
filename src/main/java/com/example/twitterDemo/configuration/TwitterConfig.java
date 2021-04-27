package com.example.twitterDemo.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

@Configuration
@ConfigurationProperties
@PropertySource("classpath:twitter4j.properties")
public class TwitterConfig {

    @Value("${twitter.consumerapi.key}")
    private String apiKey;
    @Value("${twitter.consumerapi.secretKey}")
    private String apiSecretKey;
    @Value("${twitter.oauthaccess.token}")
    private String accessToken;
    @Value("${twitter.oauthaccess.tokenSecret}")
    private String accessTokenSecret;

    @Bean
    public twitter4j.conf.Configuration configuration() {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey(this.apiKey)
                .setOAuthConsumerSecret(this.apiSecretKey)
                .setOAuthAccessToken(this.accessToken)
                .setOAuthAccessTokenSecret(this.accessTokenSecret);
        return configurationBuilder.build();
    }

    @Bean
    public Twitter configureTwitter(twitter4j.conf.Configuration configuration) {
        TwitterFactory twitterFactory = new TwitterFactory(configuration);
        return twitterFactory.getInstance();
    }

    @Bean
    TwitterStreamFactory twitterStreamFactory(twitter4j.conf.Configuration configuration) {
        return new TwitterStreamFactory(configuration);
    }

    @Bean
    TwitterStream twitterStream(TwitterStreamFactory twitterStreamFactory) {
        return twitterStreamFactory.getInstance();
    }

}
