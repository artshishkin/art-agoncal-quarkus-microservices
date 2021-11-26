package net.shyshkin.study.quarkus.microservices.book;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/books")
@Tag(name = "Book REST endpoint")
public class BookResource {

    private final BookService bookService;
    private final FallbackBookService fallback;

    public BookResource(@Named("mainBookService") BookService bookService, FallbackBookService fallback) {
        this.bookService = bookService;
        this.fallback = fallback;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Operation(
            summary = "Creates a Book",
            description = "Creates a Book with an ISBN number"
    )
    @Fallback(fallbackMethod = "fallbackCreateABook")
    @Retry(delay = 2000, maxRetries = 1)
    public Response createABook(
            @FormParam("title") String title,
            @FormParam("author") String author,
            @FormParam("year") int yearOfPublication,
            @FormParam("genre") String genre) {

        Book book = bookService.createABook(title, author, yearOfPublication, genre);

        return Response.status(201).entity(book).build();
    }

    public Response fallbackCreateABook(
            String title,
            String author,
            int yearOfPublication,
            String genre) {

        Book book = fallback.createABook(title, author, yearOfPublication, genre);

        return Response.status(206).entity(book).build();
    }

}