package com.example.twitterDemo.persistence;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface HashTagRepository extends PagingAndSortingRepository<HashTag, String> {

}
