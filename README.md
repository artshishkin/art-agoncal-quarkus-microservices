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

#####  50. Containerizing Native Linux Executables

1.  Add extension `docker`
    -  `mvn quarkus:add-extension -Dextensions=docker`
2.  Configure image group and tags
    -  in pom.xml
    -  in application.properties
3.  Configure Dockerfiles to run on port 8080
    -  `ENV QUARKUS_HTTP_PORT=8080`
4.  Containerizing JVM images
    - `mvn package -Dquarkus.container-image.build=true`
5.  Run containers (for testing build OK)
    -  `docker run -p 8702:8080 artarkatesoft/rest-book:jvm-latest` - start time 1.57s, size 525MB
       -  curl it -> ok with fallback
    -  `docker run -p 8701:8080 artarkatesoft/rest-number:jvm-latest` - start time 1.48s, size 522MB
        -  curl it -> `curl http://localhost:8701/api/numbers`
6.  Containerizing Linux Native Executables
    -  `mvn package -Dquarkus.container-image.build=true -Dquarkus.native.container-build=true -Pnative`
7.  Run
    -  `docker run -p 8702:8080 artarkatesoft/rest-book:native-latest` - start time 0.033s, size 157MB
    -  `docker run -p 8701:8080 artarkatesoft/rest-number` - start time 0.057s, size 150MB

#####  52. Executing Docker Containers

curl in Linux:
    -  native docker image - 6-9ms
    -  jvm docker image - 15-36ms

####  6 Compare with Micronaut

#####  6.1 Base Import

Feature:
    -  `openapi`

#####  6.3 Configure OpenAPI

[Generating OpenAPI Views](https://micronaut-projects.github.io/micronaut-openapi/latest/guide/index.html#openApiViews)

Specification:
    -  `http://localhost:8701/swagger/number-microservice-0.1-micronaut.yml`
Swagger-UI
    -  `http://localhost:8701/swagger-ui`
Redoc
    -  `http://localhost:8701/redoc`
Rapidoc
    -  `http://localhost:8701/rapidoc`

#####  6.5 Containerizing JVM Application

1.  View Dockerfile
    -  `gradlew dockerfile`
2.  Build docker image
    -  `gradle dockerBuild`    
3.  Run Docker App
    -  `docker run -p 8701:8080 artarkatesoft/mn-rest-number:jvm-0.1`
    -  size 340MB
    -  start time 1863ms, 1235ms

#####  6.6 Containerizing Native Linux Executable

1.  Build docker native image:
    -  `gradlew clean dockerBuildNative`
2.  Run Docker App
    -  `docker run -p 8701:8080 artarkatesoft/mn-rest-number:native-0.1`
    -  size 76.8MB
    -  start time 485ms, 138ms
