package net.shyshkin.study.quarkus.microservices.number;

import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/numbers")
public class NumberResource {

    @Inject
    IsbnGenerationService service;

    @Inject
    Logger logger;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public IsbnNumbers getIsbnNumbers() {
        var isbnNumbers = service.generateIsbnNumbers();
        logger.debug("Numbers generated: " + isbnNumbers);
        return isbnNumbers;
    }

}