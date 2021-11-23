package net.shyshkin.study.quarkus.microservices.number;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/numbers")
@Tag(name = "Number REST Endpoint")
public class NumberResource {

    @Inject
    IsbnGenerationService service;

    @Inject
    Logger logger;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Generates book numbers", description = "ISBN 13 and ISBN 10 numbers")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "ISBN Numbers", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
            @APIResponse(responseCode = "404", description = "Missing ISBN")
    })
    public IsbnNumbers getIsbnNumbers() {
        var isbnNumbers = service.generateIsbnNumbers();
        logger.debug("Numbers generated: " + isbnNumbers);
        return isbnNumbers;
    }

}