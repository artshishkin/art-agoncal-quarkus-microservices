package net.shyshkin.study.micronaut.book.proxy;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;

//@RegisterRestClient(configKey = "number.resource.proxy")
@Client("http://127.0.0.1:8701")
public interface NumberResourceProxy {

    @Get(uri = "/api/numbers", produces = MediaType.APPLICATION_JSON)
    IsbnThirteen getIsbnNumbers();

}
