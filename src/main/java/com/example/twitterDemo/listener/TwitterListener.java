package com.example.twitterDemo.listener;

import com.example.twitterDemo.mapper.TwitterMapper;
import com.example.twitterDemo.persistence.HashTag;
import com.example.twitterDemo.persistence.HashTagRepository;
import com.example.twitterDemo.persistence.Tweet;
import com.example.twitterDemo.persistence.TwitterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import twitter4j.HashtagEntity;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

import java.util.Optional;

@Component
@Slf4j
@PropertySource("classpath:application.properties")
public class TwitterListener implements StatusListener {

    @Autowired
    TwitterMapper twitterMapper;

    @Autowired
    TwitterRepository twitterRepository;

    @Autowired
    HashTagRepository hashTagRepository;
    
    @Value("${filter.followers}")
    private int followers;

    @Override
    public void onStatus(Status status) {
        if (status.getUser().getFollowersCount() >= followers) {
            Tweet tweet = twitterMapper.statusToTweet(status);
            twitterRepository.save(tweet);
            updateHashTagUses(status.getHashtagEntities());
            log.info("Status: {}", status);
        }
    }

    @Override
    public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
    }

    @Override
    public void onTrackLimitationNotice(int i) {
    }

    @Override
    public void onScrubGeo(long l, long l1) {
    }

    @Override
    public void onStallWarning(StallWarning stallWarning) {
    }

    @Override
    public void onException(Exception e) {
        log.error("ERROR", e);
    }

    private void updateHashTagUses(HashtagEntity...hashtagEntities) {
        for (HashtagEntity hashtagEntity : hashtagEntities) {
            Optional<HashTag> hashTagOptional = hashTagRepository.findById(hashtagEntity.getText());
            HashTag hashTag = hashTagOptional.orElse(HashTag.builder().id(hashtagEntity.getText()).uses(0).build());
            hashTag.setUses(hashTag.getUses() + 1);
            hashTagRepository.save(hashTag);
        }

    }
}
