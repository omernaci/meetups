FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine
COPY build/libs/ /opt/apps/
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/apps/docker-demo-0.0.1-SNAPSHOT.jar"]

