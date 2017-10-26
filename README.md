## Kotlin spring 5 springboot 2 web-flux example


Spring boot 2 app example in kotlin using reactive mongo, new spring routers and web-flux.

###Requirements
Mongo instance running on localhost:27017

###How to test app

```bash
$ ./gradlew test
```

###How to run the app


```bash
$ ./gradlew bootRun
```

###Available endpoints

GET /user		--> Get all users
GET /user/{id}	--> Get user by id
POST /user		--> Create new user

GET /role		--> Get all roles
GET /role/{id}	--> Get role by id
POST /role		--> Create new role