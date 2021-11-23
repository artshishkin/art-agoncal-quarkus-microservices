# art-agoncal-quarkus-microservices
Building Microservices with Quarkus - Tutorial from Antonio Goncalves (Udemy)

####  Section 3: Getting Started

#####  12. Bootstrapping the Application

1.  Via `https://code.quarkus.io/`
2.  Via IDE
3.  Via Maven (Win10)
```
mvn -U io.quarkus:quarkus-maven-plugin:create ^
-DprojectGroupId=net.shyshkin.study.quarkus.microservices ^
-DprojectArtifactId=rest-book ^
-DclassName="net.shyshkin.study.quarkus.microservices.book.BookResource" ^
-Dpath="/api/books" ^
-Dextensions="resteasy-jsonb"
```
For rest-number
```
mvn -U io.quarkus:quarkus-maven-plugin:create ^
-DprojectGroupId=net.shyshkin.study.quarkus.microservices ^
-DprojectArtifactId=rest-number ^
-DclassName="net.shyshkin.study.quarkus.microservices.number.NumberResource" ^
-Dpath="/api/numbers" ^
-Dextensions="resteasy-jsonb"
```
Start it
-  `mvn quarkus:dev`
Curl it
-  `curl http://localhost:8080/api/books`


