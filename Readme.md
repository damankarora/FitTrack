# FitTrack

A microservice architecture application that helps you track your activities and meals. You can track your calorie intake and calories burnt during each activity. It also gives nutrional information about the food you tracked.

## How to run

1. Clone the repository to local.
2. Paste username, password to a text file. (Refer to sample files in /creds).
3. Paste nutritionix api key, id in another text file. (Refer to sample files in /creds).
4. Update absolute path of these files in application.properties of both the services.
5. Spin up the database.
6. Run Exercise logger microservice (Will run on port 8081).

`.\mvnw clean install`

`.\mvnw spring-boot:run`

7. Run FoodLogger using the same commands (Will run on port 8080).
8. For API endpoint documentation, refer to [Swagger UI](http://localhost:8080/swagger-ui.html).

## How to test

To run the unit tests and integration tests after making changes, run 

`.\mvnw test`

