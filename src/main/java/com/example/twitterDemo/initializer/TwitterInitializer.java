package com.example.twitterDemo.initializer;

import com.example.twitterDemo.listener.TwitterListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import twitter4j.FilterQuery;
import twitter4j.TwitterStream;

@Component
@PropertySource("classpath:application.properties")
public class TwitterInitializer {

    @Value("${filter.languages}")
    private String languages;

    @Autowired
    TwitterListener twitterListener;

    @Autowired
    TwitterStream twitterStream;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        twitterStream.addListener(twitterListener);
        FilterQuery filter = new FilterQuery();
        // Filter with only language return a 406 eexception, beacause this we add theese locations
        filter.locations(new double[][] {{ -180, -90, }, { 180, 90 } }); // any geotagged tweet
        filter.language(languages.split(","));

        twitterStream.filter(filter);
    }

}
