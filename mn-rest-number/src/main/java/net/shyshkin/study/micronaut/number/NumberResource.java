package net.shyshkin.study.micronaut.number;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller("/api/numbers")
@Tag(name = "Number REST Endpoint")
public class NumberResource {

    private static final Logger logger = LoggerFactory.getLogger(NumberResource.class);

    @Inject
    IsbnGenerationService service;

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Generates book numbers", description = "ISBN 13 and ISBN 10 numbers")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ISBN Numbers", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
            @ApiResponse(responseCode = "404", description = "Missing ISBN")
    })
    public IsbnNumbers getIsbnNumbers() {
        var isbnNumbers = service.generateIsbnNumbers();
        logger.debug("Numbers generated: " + isbnNumbers);
        return isbnNumbers;
    }

}