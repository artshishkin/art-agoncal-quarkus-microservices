package net.shyshkin.study.quarkus.microservices.number;

import org.eclipse.microprofile.openapi.annotations.ExternalDocumentation;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@OpenAPIDefinition(
        info = @Info(
                title = "Number microservice",
                version = "0.0",
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
@ApplicationPath("/")
public class NumberMicroservice extends Application {
}
