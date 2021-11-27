package net.shyshkin.study.micronaut.book.proxy;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;

@Introspected
public class IsbnThirteen {

    @JsonProperty("isbn_13")
    public String isbn13;

}
