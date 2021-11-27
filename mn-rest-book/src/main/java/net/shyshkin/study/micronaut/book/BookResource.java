package net.shyshkin.study.micronaut.book;

import io.micronaut.http.HttpResponse;

public interface BookResource {

    HttpResponse<Book> createABook(
            String title,
            String author,
            int year,
            String genre);
}
