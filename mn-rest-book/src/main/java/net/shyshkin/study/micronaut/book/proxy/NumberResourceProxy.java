package net.shyshkin.study.micronaut.book.proxy;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.client.annotation.Client;

//@RegisterRestClient(configKey = "number.resource.proxy")
@Client("/api/numbers")
public interface NumberResourceProxy {

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    IsbnThirteen getIsbnNumbers();

}
