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

#####  20. Documenting the Number Microservice

OpenAPI Contract:
    -  `http://localhost:8080/q/openapi`
    -  in yaml or json
Swagger-UI:
    -  `http://localhost:8080/q/swagger-ui/`   

####  Section 5: Developing the Book Microservice

#####  28. Exposing the Book REST Endpoint

Post a new Book
-  `curl -X POST http://localhost:8080/api/books -d "title=My great book&year=2020&author=Art Shyshkin&genre=Fantasy"`

####  Section 7: Executing the Application

#####  48. Building Native Linux Executables

1.  Build native executables for Windows
    -  in PowerShell run
    -  `cmd.exe /c 'call "c:\Program Files (x86)\Microsoft Visual Studio\2017\BuildTools\VC\Auxiliary\Build\vcvars64.bat" && mvn clean package -Pnative' `
    -  run
        -  `target\rest-number-1.0.0-SNAPSHOT-runner.exe` - start time 146ms, size 48MB
        -  `target\rest-book-1.0.0-SNAPSHOT-runner.exe` - start time 126ms, size 56MB
    -  curl
        -  using Postman - 6-7ms
        -  using Intellij - 47ms
2.  Build/test native executables for Linux (natively)
    -  `git clone https://github.com/artshishkin/art-agoncal-quarkus-microservices.git`
    -  `chmod +x mvnw`
    -  `./mvnw package -Pnative` - build native executable
    -  `./mvnw verify -Pnative` - build and native test
    -  run
        -  `./target/rest-number-1.0.0-SNAPSHOT-runner` - start time 19ms, size 48MB
        -  `./target/rest-book-1.0.0-SNAPSHOT-runner` - start time 23ms, size 55MB
    -  curl with time measure (total time ~ 4ms)    
```shell
curl --location --request POST 'http://localhost:8702/api/books' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'title=You are the great' \
--data-urlencode 'yearOfPublication=2020' \
--data-urlencode 'genre=Western' \
--data-urlencode 'author=Art' \
-w %{time_connect}:%{time_starttransfer}:%{time_total}
```    
3.  Build native executables for Linux (from Windows in Docker)
    -  `mvn package -Dquarkus.native.container-build=true -Pnative`
