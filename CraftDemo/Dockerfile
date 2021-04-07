FROM adoptopenjdk/openjdk11:alpine-jre
ADD target/craftdemo-0.0.1-SNAPSHOT.jar app.jar
ENV PROFILE=docker
ENTRYPOINT ["java","-jar", "app.jar", "--spring.profiles.active=${PROFILE}"]
#ENTRYPOINT ["java","-jar", "app.jar"]