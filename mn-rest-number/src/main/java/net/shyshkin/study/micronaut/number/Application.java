package net.shyshkin.study.micronaut.number;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.tags.Tag;

@OpenAPIDefinition(
        info = @Info(
                title = "Number microservice",
                version = "0.1-micronaut",
                description = "This microservice generates book numbers",
                license = @License(name = "MIT"),
                contact = @Contact(name = "art", url = "http://shyshkin.net", email = "d.art.shishkin@gmail.com")
        ),
        externalDocs = @ExternalDocumentation(url = "https://github.com/artshishkin/art-agoncal-quarkus-microservices/tree/main/rest-number", description = "All the source code of microservice"),
        tags = {
                @Tag(name = "api", description = "Public API that can be used by anybody"),
                @Tag(name = "numbers", description = "Anybody interested in numbers"),
        }
)
public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}
