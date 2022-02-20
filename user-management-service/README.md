# Usermanagement REST Endpoints
This project consits of two microservices 
- Get User Details
- Update User Details

# Building the application
Pre-Requisits 
Java 8
Maven 3.8.4

# Running the application
Please run following command to start the services
cd user-management-service
mvn spring-boot:run

# API Endpoints
GET http://localhost:8080/api/userdetails/{id}
PUT http://localhost:8080/api/userdetails/{id}

# Testing the APIs using Postman
Use Postman collection under path src/postman/collections to test the application
ING_USER_MANAGEMENT_SERVICE_SMOKE.postman_collection.json

Select Authorization type as Basci Auth

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
url: http://localhost:8080/h2-ui

# Assumptions and Approaches 
empid field is mapped to data type NUMERIC(10), because it's mentioned that to validate the user id to have only numeric. Given empid in the payload is length of 7 and above 1 million
1000000
1232854
therefore selecting a numeric value size 10

EmpId considered as unique and further seperate index added to the column, however unique constrain also creats an index, but creating seperate index will give the felxibility in selecting index types based on the database. 

 
other fields data type and length values are selected to satisfy minimum space usage of DB and consideration of practical lengths ex:postcode 

all the values coming from client will be stored as it is, there will no application of changing text to upper or lower case.
ex: mr -> mr, male->male , female->female

if user updates employee id ,client should update the api url path.



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


         
   
         
          