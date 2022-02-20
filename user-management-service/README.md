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

# Project Structure
src/main/java
com
 +- ing
     +- usermanagement
         +- UserManagementServiceApplication.java
         |
         +- controller
         |   +- UserController
         |
         +- service
             +- UserManagementService.java
             |
             +- impl
                +- UserManagementServiceImpl.java
         |
         +- repository
             +- UserRepository.java
         |
         +- model
             +- BaseModel.java
             +- Address.java
             +- User.java
         +- dto
             +- AddressDto.java
             +- UserDto.java
             +- User.java
         +- mapper
             +- UserMapperUtil.java
         + exception
             +- CustomExceptionHandler.java
         + aop
             +- LoggingAspect.java
         + security 
             +- SecurityConfig.java
             +- UserManagementAuthenticationEntryPoint.java
             
src/test/java

.
└── com
    └── ing
        └── usermanagement
            ├── UserManagementServiceApplication.java
            ├── aop
            │   └── LoggingAspect.java
            ├── controller
            │   └── UserController.java
            ├── dto
            │   ├── AddressDto.java
            │   ├── ErrorDto.java
            │   └── UserDto.java
            ├── exception
            │   ├── CustomExceptionHandler.java
            │   ├── DataBaseServiceUnavailableException.java
            │   ├── ResourceNotFoundException.java
            │   └── ServiceApiException.java
            ├── mapper
            │   └── UserMapperUtil.java
            ├── model
            │   ├── Address.java
            │   ├── BaseModel.java
            │   └── User.java
            ├── repository
            │   └── UserRepository.java
            ├── security
            │   ├── SecurityConfig.java
            │   └── UserManagementAuthenticationEntryPoint.java
            └── service
                ├── UserManagementService.java
                └── impl
                    └── UserManagementServiceImpl.java



         
Assumptions and Approaches    
         
          