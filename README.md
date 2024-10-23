# CapWSB - FitenssTracker

## Run server
```bash
mvn spring-boot:run
```

## Start server
```bash
mvn spring-boot:start
```

## Stop server
```bash
mvn spring-boot:stop
```

## Run tests
```bash
mvn test
```

## Run individual test
```bash
mvn -Dtest=<TestName> test
```
## Database H2
[DATABASE_LINK](http://localhost:8081/h2-console/login.do?jsessionid=636419c35d778c1b416a8c04115d75a7#
)

## Postman Collection API
Wyeksportowana kolekcja zapytań z programu Postman, pozwalająca przetestować stworzone API:
### users 
- src\test\resources\collections\CapWSB-FitnessTracker.postman_user_collection.json