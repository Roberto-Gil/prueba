package com.example.twitterDemo.mapper;

import com.example.twitterDemo.persistence.Tweet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import twitter4j.Status;

@Mapper
public interface TwitterMapper {
    @Mappings({
        @Mapping(target="userId", source="user.id"),
        @Mapping(target="location", source="user.location"),
        @Mapping(target="text", source="text")
    })
    Tweet statusToTweet(Status status);
}
