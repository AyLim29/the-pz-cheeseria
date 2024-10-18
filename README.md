The PZ Cheeseria Proof of Concept
============================
> Minimim viable product that displays The PZ Cheeseria's cheese selection

<figure>
  <img
  src="/images/PZ_C_POC1.PNG">
  <center><figcaption>5 cheeses being displayed</figcaption></center>
</figure>
<br/>
<figure>
  <img
  src="/images/PZ_C_POC2.PNG">
  <center><figcaption>Price calculator for cheese weight</figcaption></center>
</figure>

---

### File Strucutre
    .
    ├── cheeseria-backend                                           # The back-end of the App, using spring boot       
    │   ├── src 
    │   │   ├── ...
    │   │   ├── main
    │   │   │   ├── java/com/cheeseria/backend
    │   │   │   │   ├── controller
    │   │   │   │   │   ├── CheeseController.java               
    │   │   │   │   ├── model
    │   │   │   │   │   ├── CheeseModel.java
    │   │   │   │   ├── repository
    │   │   │   │   │   └── CheeseRepository.java
    │   │   │   │   └── CheeseriaBackendApplication.java            # Runs the back-end
    │   │   │   └── resources
    │   │   │       ├── ...
    │   │   │       ├── application.properties                      
    │   │   │       └── data.sql                                    # The prepopulated data of 5 cheeses
    │   │   └── test/java/com/cheeseria/backend                     # The tests files for the back-end
    │   │       ├── controller
    │   │       │   ├── CheeseControllerTest.java                   
    │   │       └── repository
    │   │           └── CheeseRepositoryTest.java
    │   ├── dockerfile        
    │   ├── ...
    │   └── pom.xml      
    │                 
    ├── cheeseria-frontend                                          # The front-end of the App, using React 
    │   ├── .docker          
    │   │   └── nginx.conf                                          
    │   ├── ...
    │   ├── src
    │   │   ├── _mocks_
    │   │   │   └── mock_data.js                                    # Mock data for testing
    │   │   ├── App.css                                             
    │   │   ├── App.js
    │   │   ├── AppTest.js
    │   │   └── ...
    │   ├── dockerfile
    │   ├── package-lock.json
    │   └── package.json
    │
    ├── docker-compose.yml
    └── README.md


## Technologies Used
- Java with Spring Boot
- H2 Database
- Swagger/ Open API
- React 
- Docker

## Testing
##### Back-End Testing
To test the back-end, run the following in ./cheeseria-backend:

    mvn test

The test files used for this test are located in `./cheeseria-backend/src/test/...`


##### Front-End Testing
To test the front-end, run the following in ./cheeseria-backend

    npm test

The test file used for this test are located in `./cheeseria-frontend/src/App.test.js`

## Building The Project
To build and run the app, use the following in the project's root directory:
```docker-compose up --build```

This will build a docker image for both the front-end and the back-end and start the application.

Visit http://localhost:3000 to access the frontend.
Visit http://localhost:8080/v3/api-docs to access the api docs generated by OpenAPI and
http://localhost:8080/swagger-ui/index.html to access the Swagger UI


## Future Implementation
Features that I would like to include if I had more time are included as comments with the ``TODO:`` tag. 
