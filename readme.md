## 1. To Build : 
```sh
mvn clean package
```
## 2. To run
```sh
    java  -Dspring.social.twitter.app-id={twitter app id}  -Dspring.social.twitter.app-secret={twitter secret}  -jar grid-reactive-1.0.0.jar
```    
## 3. To query
> To query the service we can pass a search string : 
```sh
    curl -X GET localhost:8080/tweets?search=scala
```
    
> To query the service with default search i.e. 'Reactive' : 
```sh
    curl -X GET localhost:8080/tweets
```
