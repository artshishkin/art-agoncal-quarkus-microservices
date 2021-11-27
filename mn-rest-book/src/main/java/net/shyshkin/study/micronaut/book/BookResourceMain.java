package net.shyshkin.study.micronaut.book;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.retry.annotation.Recoverable;
import io.micronaut.retry.annotation.Retryable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/api/books")
@Tag(name = "Book REST endpoint")
@Recoverable(api = BookResourceFallback.class)
public class BookResourceMain implements BookResource {

    private final static Logger logger = LoggerFactory.getLogger(BookResourceMain.class);

    private final BookService bookService;

    public BookResourceMain(@Named("mainBookService") BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    @Post
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Operation(
            summary = "Creates a Book",
            description = "Creates a Book with an ISBN number"
    )
    @Retryable(delay = "900ms", attempts = "2", multiplier = "0.9")
    public HttpResponse<Book> createABook(
            String title,
            String author,
            int year,
            String genre) {

        try {
            logger.info("Trying to create a book");
            Book book = bookService.createABook(title, author, year, genre);
            return HttpResponse.status(HttpStatus.CREATED).body(book);
        } catch (Exception e) {
            logger.warn("Error in creating book: {}", e.getMessage());
            throw e;
        }
    }

}