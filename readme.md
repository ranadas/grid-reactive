
## 1. To Build : 
    mvn clean package
## 2. To run
    java  -Dspring.social.twitter.app-id={twitter app id}  -Dspring.social.twitter.app-secret={twitter secret}  -jar grid-reactive-1.0.0.jar
## 3. To query
   #### To query the service we can pass a search string : 
        curl -X GET localhost:8080/tweets?search=scala
   #### To query the service with default search i.e. 'Reactive' : 
        curl -X GET localhost:8080/tweets
    
