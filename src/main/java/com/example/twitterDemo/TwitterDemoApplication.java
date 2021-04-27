package com.example.twitterDemo;

import com.example.twitterDemo.configuration.TwitterConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(TwitterConfig.class)
public class TwitterDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwitterDemoApplication.class, args);
	}

}
