# Web Service Testing 
Functional and Load Testing

### Starting PetClinic API
[PetClinic Github](https://github.com/spring-petclinic/spring-petclinic-rest)
```
git clone https://github.com/spring-petclinic/spring-petclinic-rest.git
cd spring-petclinic-rest
./mvnw spring-boot:run
```

### Preconditions for test execution (Pet Clinic):
- [x] JDK 8+
- [x] MAVEN 3.5+
- [x] PetClinic API running
- [x] Swagger docs opens at http://localhost:9966/petclinic

### Running tests:
```
mvn clean test
```

### Running specific tag:
```
mvn clean test -Dgroups=smoke
```
