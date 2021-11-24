package net.shyshkin.study.quarkus.microservices.number;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class NumberResourceTest {

    @Test
    public void testHelloEndpoint() {

        given()

                .when().get("/api/numbers")

                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body(CoreMatchers.notNullValue())
                .body("isbn_10", not(emptyOrNullString()))
                .body("isbn_10", startsWith("10-"))
                .body("isbn_13", startsWith("13-"))
                .body("generationDate", nullValue())
        ;
    }

}