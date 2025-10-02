# start with best image containing java runtime
FROM openjdk:17 as build

# Information of own or maintaner image
MAINTAINER makaraphoneshop

# add the application's jar to conatiner
COPY target/phoneshop-0.0.1-SNAPSHOT.jar phoneshop-0.0.1-SNAPSHOT.jar

# Excute the application
ENTRYPOINT ["java", "-jar","/phoneshop-0.0.1-SNAPSHOT.jar"]