FROM ubuntu:latest

MAINTAINER Rana Das <rana.pratap.das@gmail.com>

RUN apt-get update \
  && apt-get install -y vim   \
  && apt-get install -y curl

#Install openjdk
RUN apt-get update && \
	apt-get install -y openjdk-8-jdk && \
	apt-get install -y ant && \
	apt-get clean && \
	rm -rf /var/lib/apt/lists/* && \
	rm -rf /var/cache/oracle-jdk8-installer;

# Fix certificate issues, found as of
# https://bugs.launchpad.net/ubuntu/+source/ca-certificates-java/+bug/983302
RUN apt-get update && \
	apt-get install -y ca-certificates-java && \
	apt-get clean && \
	update-ca-certificates -f && \
	rm -rf /var/lib/apt/lists/* && \
	rm -rf /var/cache/oracle-jdk8-installer;

# Setup JAVA_HOME
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64/
RUN export JAVA_HOME

RUN	mkdir /api
WORKDIR /api

ADD target/grid-reactive.jar  boot-app.jar
RUN chmod 777 -R /api

ENTRYPOINT ["java" ,"-Djava.security.egd=file:/dev/./urandom", "-jar" ,"boot-app.jar"]
EXPOSE 8080




