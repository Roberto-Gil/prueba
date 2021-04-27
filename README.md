# prueba
# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.5/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.5/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.4.5/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.4.5/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Twitter4j] (https://twitter4j.org/en/)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

This microservice creates a listener that listens for tweet traffic and persists in memory those that meet the criteria.
On the other hand, the Api Rest consults the data stored in memory.

ApiRest:
Postman collection: ...src/main/resources/postman/New Collection.postman_collection.json

localhost:8080/Tweets -> get all tweets persisted in memory
localhost:8080/Tweets/hashtag -> get n most used hashtags defined in filter.hashtags, default = 10
localhost:8080/Tweets/validated -> get all tweets validated by the user
localhost:8080/Tweets/:id/validate -> validate the tweet with :id id

Twitter Streaming Api listener:
Filer Query ->  any geotagged tweet with languages defined in filter.languages, default = es, fr, it
Filter in Listener -> tweets from users with a number o followers defined in filter.followers, default = 1500
Hashtags -> update the number of uses of a hashtag.

Persistence:
Tweet -> (id, userId, text, location, validation)
HashTag -> (id, uses)
