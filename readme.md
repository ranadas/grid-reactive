##A Objective : 
Simple command line API mashup of GitHub and Twitter APIs. Search for "Reactive" projects on GitHub, then for each project search for tweets that mention it. You should output a summary of each project with a short list of recent tweets, in JSON format.

##Prerequisites
    * We would need Twitter app id and secret key to be able to use.
    * Java 8  

#Getting Started: 
## 1. To run tests & build the artifact: 
```sh
mvn clean -Dspring.social.twitter.app-id={twitter app id}  -Dspring.social.twitter.app-secret={twitter secret} package
```
## 2. To run : 
Go to the folder that has the artifact, e.g. target folder after building.
```sh
    java  -Dspring.social.twitter.app-id={twitter app id}  -Dspring.social.twitter.app-secret={twitter secret}  -jar grid-reactive.jar
``` 
##There is only one limit on this service. 
Its the limit from Twitter.  Its defaulted to 10. To change the tweet search limit, which is set to 10 now, we can override in the startup.

```sh
    -Dspring.social.twitter.ratelimit=20
```

## 3. To query
> To query the service with default search (i.e. 'Reactive') : 
```sh
    curl -X GET localhost:8080/tweets
```
> To query the service we can pass a search string : 
```sh
    curl -X GET localhost:8080/tweets?search=scala
```
    
#Under the Hood:
This is a Spring SpringBoot application with Java. 
Have used spring-boot-social-twitter for twitter searches.
Undertow serving the application instead of default tomcat container.
  
  
### Finally we can also run this in a Docker container.
After building the artifact, we can build the image by : 
 ```sh
docker build -t workday-testimg  . 
 ``` 
And Run by  
```sh
docker run -d -p8080:8080 -t --name grid-reactive workday-testimg 
 ```  
 
 https://github.com/spring-projects/spring-social-samples/blob/master/spring-social-showcase/src/main/java/org/springframework/social/showcase/ExceptionHandlingControllerAdvice.java