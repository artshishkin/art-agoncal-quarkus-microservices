package net.shyshkin.study.micronaut.book;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/api/books")
@Tag(name = "Book REST endpoint")
public class BookResource {

    private final static Logger logger = LoggerFactory.getLogger(BookResource.class);

    private final BookService bookService;
    private final FallbackBookService fallback;

    public BookResource(@Named("mainBookService") BookService bookService, FallbackBookService fallback) {
        this.bookService = bookService;
        this.fallback = fallback;
    }

    @Post
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Operation(
            summary = "Creates a Book",
            description = "Creates a Book with an ISBN number"
    )
//    @Fallback(fallbackMethod = "fallbackCreateABook")
//    @Retry(delay = 900, maxRetries = 2)
    public HttpResponse<Book> createABook(
             String title,
             String author,
             int year,
             String genre) {

        try {
            logger.info("Trying to create a book");
            Book book = fallback.createABook(title, author, year, genre);
//            Book book = bookService.createABook(title, author, yearOfPublication, genre);
            return HttpResponse.status(HttpStatus.CREATED).body(book);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            throw e;
        }
    }

//    public Response fallbackCreateABook(
//            String title,
//            String author,
//            int yearOfPublication,
//            String genre) {
//
//        Book book = fallback.createABook(title, author, yearOfPublication, genre);
//
//        return Response.status(206).entity(book).build();
//    }

}