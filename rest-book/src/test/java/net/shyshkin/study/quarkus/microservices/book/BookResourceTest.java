package net.shyshkin.study.quarkus.microservices.book;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;

@QuarkusTest
public class BookResourceTest {

    @Test
    public void shouldCreateBook() {
        given()
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON)
                .formParams(
                        "title", "My great book",
                        "year", 2020,
                        "author", "Art Shyshkin",
                        "genre", "Fantasy"
                )

                .when().post("/api/books")

                .then()
                .statusCode(201)
                .body("author", is("Art Shyshkin"))
                .body("genre", is("Fantasy"))
                .body("isbn_13", is("We need to get ISBN from external service"))
                .body("title", is("My great book"))
                .body("year_of_publication", is(2020))
                .body("creation_date", is(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"))))
                .body("creation_date", startsWith("20"))
        ;
    }

}