### Task
Build an API which calls this API, and returns people who are listed as either living in London, or whose current coordinates are within 50 miles of London.
### Dependencies
- Spring Boot
- JUnit
- Geotools

### Run Tests
```
mvn test
```
### Build and Run App
```
mvn clean install
java -jar target/tech-test-0.0.1-SNAPSHOT.jar
```
OR
```
docker build -t adeel-dwp-test .  
docker run -p 8080:8080 adeel-dwp-test
```


## API call

GET ```http://localhost:8080/users/london```
```bash
curl -X GET "http://localhost:8080/users/london" -H "accept: application/json"
```
