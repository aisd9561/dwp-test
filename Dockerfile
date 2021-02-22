FROM openjdk:11
WORKDIR /
COPY ./target/tech-test-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java","-jar","tech-test-0.0.1-SNAPSHOT.jar"]