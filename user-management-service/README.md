# Usermanagement REST Endpoints
This project consists of two microservices 
- Get User Details
- Update User Details

# Building the application
Pre-Requisites 
Java 8
Maven 3.8.4

# Running the application
Please run the following command to start the services
cd user-management-service
mvn spring-boot:run

# API Endpoints
GET http://localhost:8080/api/userdetails/{id}
PUT http://localhost:8080/api/userdetails/{id}

# Testing the APIs using Postman + Basic Authentication
Use Postman collection under path src/postman/collections to test the application
ING_USER_MANAGEMENT_SERVICE_SMOKE.postman_collection.json

Select Authorisation type as Basic Auth

For GET API 
use user credentials 
Username : user123
Password : user123

For PUT API
use admin credentials
Username : admin
Password : admin


# DB login details:
jdbc url: jdbc:h2:mem:user_db
username: admin
password: admin
url: http://localhost:8080/h2-console

# Assumptions and Approaches 
empid field is mapped to data type NUMERIC(10), because it's mentioned that to validate the user id to have only numeric. 
Given empid in the payload is length of 7 and above 1 million
1000000
1232854
therefore selecting a numeric value size  as 10

EmpId has been considered as unique and further separate index added to the column, however unique constrain also creates an index, but creating a separate index will give the flexibility in selecting index types based on the database. 

 
other fields data type and length values are selected to satisfy minimum space usage of DB and consideration of practical lengths ex:postcode 

All the values coming from the client will be stored as it is in the database, and there will be no application of changing text to upper or lower case.
ex: mr -> mr, male->male , female->female
 

Error message structure
errorCode was introduced mainly to utilise in observability platform integration and build better insights on application behaviour 
{
    "message": "Invalid Request",
    "errorCode": "RC_INVR",
    "details": [
        "gender: Gender is required"
    ]
}

{
    "message": "Invalid Parameter Type",
    "errorCode": "RC_001",
    "details": [
        "empid: 1232857wefw"
    ]
}

# Circuit breaker implementation and testing

Configurations for circuitbreaker is countbased and circuit will open 40% failures 
resilience4j.circuitbreaker.instances.getInvoiceCB.failure-rate-threshold=40
resilience4j.circuitbreaker.instances.getInvoiceCB.sliding-window-size=5
resilience4j.circuitbreaker.instances.getInvoiceCB.sliding-window-type=COUNT_BASED
resilience4j.circuitbreaker.instances.getInvoiceCB.minimum-number-of-calls=5
resilience4j.circuitbreaker.instances.getInvoiceCB.automatic-transition-from-open-to-half-open-enabled=true
resilience4j.circuitbreaker.instances.getInvoiceCB.permitted-number-of-calls-in-half-open-state=1
resilience4j.circuitbreaker.instances.getInvoiceCB.wait-duration-in-open-state=1s

Please use the 400 - PUT_UPDATE_USER_DETAILS_(Circuit Breaker) to test the circuit breaker
drop the user table by accessing h2 db using http://localhost:8080/h2-console
DROP TABLE ING_USER ;
After a successful table drop, please invoke the test.
Expected result:
{
    "message": "Thanks for your request, We appologise for the issue, please come back later",
    "errorCode": "SYS_000",
    "details": [
        "Circuit Breaker-updateUserFallback"
    ]
}

{
    "message": "Thanks for your request, We apologise for the issue, please come back later",
    "errorCode": "SYS_000",
    "details": [
        "Circuit Breaker-getUserFallback"
    ]
}

# Implement the entry/exit logging
Refer LoggingAspect.java @Around applied on application package pointcuts


# Unit tests, Integration and Consumer Contract testing
UserControllerTest - e2e integration test
UserManagementRepositoryTest - db integration test
UsermanagementServiceTest - Junit test
ING_USER_MANAGEMENT_SERVICE_SMOKE.postman_collection.json - smoke suite
pact test - pending.




# Basic User authentication and verification
Use following test cases to validate this feature
401 - GET_USER_DETAILS  - correct username invalid password
401 - GET_USER_DETAILS (invalid username) -invalid username
401 - PUT_UPDATE_USER_DETAILS - incorrect username and password
403 - PUT_UPDATE_USER_DETAILS - User with USER role not allowed
200 - GET_USER_DETAILS(admin) - allowed for admin




# Project Structure

├── HELP.md
├── README.md
├── mvnw
├── mvnw.cmd
├── pom.xml
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── ing
    │   │           └── usermanagement
    │   │               ├── UserManagementServiceApplication.java
    │   │               ├── aop
    │   │               │   └── LoggingAspect.java
    │   │               ├── controller
    │   │               │   └── UserController.java
    │   │               ├── dto
    │   │               │   ├── AddressDto.java
    │   │               │   ├── ErrorDto.java
    │   │               │   └── UserDto.java
    │   │               ├── exception
    │   │               │   ├── CustomExceptionHandler.java
    │   │               │   ├── DataBaseServiceUnavailableException.java
    │   │               │   ├── ResourceNotFoundException.java
    │   │               │   └── ServiceApiException.java
    │   │               ├── mapper
    │   │               │   └── UserMapperUtil.java
    │   │               ├── model
    │   │               │   ├── Address.java
    │   │               │   ├── BaseModel.java
    │   │               │   └── User.java
    │   │               ├── repository
    │   │               │   └── UserRepository.java
    │   │               ├── security
    │   │               │   ├── SecurityConfig.java
    │   │               │   └── UserManagementAuthenticationEntryPoint.java
    │   │               └── service
    │   │                   ├── UserManagementService.java
    │   │                   └── impl
    │   │                       └── UserManagementServiceImpl.java
    │   └── resources
    │       ├── application-test.properties
    │       ├── application.properties
    │       ├── data.sql
    │       └── schema.sql
    ├── postman
    │   └── collections
    │       └── ING_USER_MANAGEMENT_SERVICE_SMOKE.postman_collection.json
    └── test
        └── java
            └── com
                └── ing
                    └── usermanagement
                        ├── BaseTest.java
                        ├── contract
                        │   ├── UserDetailsRequestResponsePact.java
                        │   └── UsermanagementContractTest.java
                        ├── controller
                        │   └── UserControllerTest.java
                        ├── repository
                        │   └── UserManagementRepositoryTest.java
                        └── service


         
   
         
          