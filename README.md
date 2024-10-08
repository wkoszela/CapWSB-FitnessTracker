Start server:
mvn -f 1.0.0-100407-100162-SNAPSHOT.xml spring-boot:start
Stop server:
mvn -f 1.0.0-100407-100162-SNAPSHOT.xml spring-boot:stop
Run tests:
mvn -f 1.0.0-100407-100162-SNAPSHOT.xml test
Run individual test
mvn -f 1.0.0-100407-100162-SNAPSHOT.xml -Dtest=<TestName> test

database H2:
http://localhost:8080/h2-console/login.do?jsessionid=636419c35d778c1b416a8c04115d75a7#

