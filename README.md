# User Details Microservices

## Usermanagement REST Endpoints

This project consists of two microservices 
- Get User Details : GET http://localhost:8080/api/userdetails/{id}
- Update User Details : PUT http://localhost:8080/api/userdetails/{id}

# Pre-Requisites Building the application 
- Java 8
- Maven 3.8.4

## Installation
Please run the following command to start the services
- cd user-management-service
- mvn spring-boot:run

## Testing the APIs using Postman + Basic Authentication
- Use Postman collection under path src/postman/collections to test the application
ING_USER_MANAGEMENT_SERVICE_SMOKE.postman_collection.json

- Select Authorisation type as Basic Auth

- For GET API 
use user credentials 
```bash
Username : user123
Password : user123
```

- For PUT API
use admin credentials
```bash
Username : admin
Password : admin
```

## DB login details:
```bash
jdbc url: jdbc:h2:mem:user_db
username: admin
password: admin
url: http://localhost:8080/h2-console 
```

## Assumptions and Approach

empid field is mapped to data type NUMERIC(10), because it's mentioned that to validate the user id to have only numeric. 
Given empid in the payload is length of 7 and above 1 million
1000000
1232854
therefore selecting a numeric value size  as 10

EmpId has been considered as a unique index and further separate index added to the column. However, unique constrain also creates an index, but creating a separate index will give the flexibility in selecting more specific index type. 

 
Other fields data type and length values are selected to satisfy minimum space usage of DB and consideration of practical lengths ex:postcode 

All the values coming from the client will be stored as it is in the database, and there will be no application of changing text to upper or lower case.
ex: mr -> mr, male->male , female->female
 
## Error message structure
errorCode was introduced mainly to utilise in observability platform integration and build better insights on application behaviour 
```bash
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
```
## Circuit breaker implementation and testing
Please use the 500 - PUT_UPDATE_USER_DETAILS_(Circuit Breaker) to test the circuit breaker
drop the ING_USER user table before accessing h2 db using http://localhost:8080/h2-console

```bash
DROP TABLE ING_USER ;
```

After a successful table drop, please invoke the test.
Expected result:
```bash
{
    "message": "Thanks for your request, We appologise for the issue, please come back later",
    "errorCode": "SYS_000",
    "details": [
        "Circuit Breaker-updateUserFallback"
    ]
}
```

Transaction Rollback handling.
- User update service UserManagementServiceImpl.java

```bash
@Override
	@CircuitBreaker(name = "updateUser", fallbackMethod = "updateUserFallback")
	@Transactional
	public UserDto updateUser(Long empId, UserDto userDto) {
		User user = userRepository.findByEmployeeId(empId)
				.orElseThrow(() -> new ResourceNotFoundException("Unable to find the user"));
		user.setTitle(userDto.getTitle());
		user.setFirstName(userDto.getFirstn());
		user.setGender(userDto.getGender());
		user.setEmployeeId(Long.valueOf(userDto.getEmpid()));
		Address address = user.getAddress();
		if (address != null) {
			address.setCity(userDto.getAddressDto().getCity());
			address.setPostcode(userDto.getAddressDto().getPostcode());
		}
		user = userRepository.save(user);
		return UserMapperUtil.toUserDto(user, new UserDto());
	}
```

- UserRepository.java 
```bash
@Transactional(readOnly = true) 
```


```bash
{
    "message": "Thanks for your request, We apologise for the issue, please come back later",
    "errorCode": "SYS_000",
    "details": [
        "Circuit Breaker-getUserFallback"
    ]
}
```



## Implement the entry/exit logging
- Refer LoggingAspect.java @Around applied on application package pointcuts

```bash
@Pointcut("within(com.ing.usermanagement.controller..*)" +
        " || within(com.ing.usermanagement.service..*)" + 
        " || within(com.ing.usermanagement.repository..*)")
    public void applicationPackagePointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }
```



```bash
    @Around("applicationPackagePointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        if (log.isDebugEnabled()) {
            log.debug("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        }
        try {
            Object result = joinPoint.proceed();
            if (log.isDebugEnabled()) {
                log.debug("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), result);
            }
            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
                joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            throw e;
        }
    }

```

# Unit tests, Integration and Consumer Contract testing

- UserControllerTest - e2e integration test
- UserManagementRepositoryTest - db integration test
- UsermanagementServiceTest - Junit test
- ING_USER_MANAGEMENT_SERVICE_SMOKE.postman_collection.json - smoke suite
- pact test consumer contract generation and validation - refer consumer-user-management-service 
- pact test provider validation was causing error, please uncomment PactVerificationTest to verify [its giving an issue due to security validations :)]
pact folder is located under user-management-service.

# Basic User authentication and verification

Use following test cases to validate this feature
- 401 - GET_USER_DETAILS  - correct username invalid password
- 401 - GET_USER_DETAILS (invalid username) -invalid username
- 401 - PUT_UPDATE_USER_DETAILS - incorrect username and password
- 403 - PUT_UPDATE_USER_DETAILS - User with USER role not allowed
- 200 - GET_USER_DETAILS(admin) - allowed for admin

