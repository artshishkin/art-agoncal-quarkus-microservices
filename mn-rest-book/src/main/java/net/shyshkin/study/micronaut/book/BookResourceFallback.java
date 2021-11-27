package net.shyshkin.study.micronaut.book;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.retry.annotation.Fallback;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@Fallback
public class BookResourceFallback implements BookResource {

    private final static Logger logger = LoggerFactory.getLogger(BookResourceFallback.class);

    private final FallbackBookService fallback;

    public BookResourceFallback(FallbackBookService fallback) {
        this.fallback = fallback;
    }

    @Override
    public HttpResponse<Book> createABook(
            String title,
            String author,
            int yearOfPublication,
            String genre) {

        Book book = fallback.createABook(title, author, yearOfPublication, genre);

        return HttpResponse.status(HttpStatus.PARTIAL_CONTENT).body(book);
    }
}