package net.shyshkin.study.quarkus.microservices.book;

import net.shyshkin.study.quarkus.microservices.book.proxy.IsbnThirteen;
import net.shyshkin.study.quarkus.microservices.book.proxy.NumberResourceProxy;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.time.Instant;

@ApplicationScoped
@Named("mainBookService")
public class BookServiceImpl implements BookService {

    @RestClient
    NumberResourceProxy proxy;

    @Inject
    Logger logger;

    @Override
    public Book createABook(String title, String author, int yearOfPublication, String genre) {

        IsbnThirteen isbnNumbers = proxy.getIsbnNumbers();

        Book book = new Book();
        book.setIsbn13(isbnNumbers.isbn13);
        book.setTitle(title);
        book.setAuthor(author);
        book.setYearOfPublication(yearOfPublication);
        book.setGenre(genre);
        book.setCreationDate(Instant.now());

        logger.info("Book created: " + book);

        return book;
    }
}
