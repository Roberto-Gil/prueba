package com.example.twitterDemo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TwitterRepository extends JpaRepository<Tweet, Long> {

    @Modifying
    @Query("update Tweet t set t.validated = true where t.id = :id")
    @Transactional
    int setValidated(Long id);

    List<Tweet> findByValidated(boolean validated);


}
