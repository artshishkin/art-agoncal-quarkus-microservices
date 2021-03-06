package net.shyshkin.study.quarkus.microservices.book.proxy;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RegisterRestClient(configKey = "number.resource.proxy")
@Path("/api/numbers")
public interface NumberResourceProxy {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    IsbnThirteen getIsbnNumbers();

}
