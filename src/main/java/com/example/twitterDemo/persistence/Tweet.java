package com.example.twitterDemo.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Tweet {
    private @Id @GeneratedValue long id;
    private long userId;
    @Column(columnDefinition="TEXT")
    private String text;
    private String location;
    private boolean validated;
}
