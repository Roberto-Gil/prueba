package com.example.twitterDemo.controller;

import com.example.twitterDemo.persistence.HashTag;
import com.example.twitterDemo.persistence.HashTagRepository;
import com.example.twitterDemo.persistence.Tweet;
import com.example.twitterDemo.persistence.TwitterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController()
@PropertySource("classpath:application.properties")
@RequestMapping("/Tweets")
public class TwitterRestController {

    @Autowired
    TwitterRepository twitterRepository;

    @Autowired
    HashTagRepository hashTagRepository;

    @Value("${filter.hashtags}")
    private int numberOfHastags;

    @GetMapping()
    public List<Tweet> getTweets() {
        log.debug("getTweets");
        return twitterRepository.findAll();
    }

    @PutMapping("/{id}/validate")
    public void validateTweet(@PathVariable Long id) {
        log.debug("validateTweet {}", id);
        twitterRepository.setValidated(id);
    }

    @GetMapping("/validated")
    public List<Tweet> getValidatedTweets() {
        log.debug("getValidatedTweets");
        return twitterRepository.findByValidated(true);
    }

    @GetMapping("/hashtag")
    public List<HashTag> getHashtags() {
        log.debug("getHashtags");
        Pageable page = PageRequest.of(0, numberOfHastags, Sort.by("uses").descending());
        Page<HashTag> hashTags = hashTagRepository.findAll(page);
        return hashTags.stream().collect(Collectors.toList());
    }

}
