package net.shyshkin.study.micronaut.book;

import io.micronaut.http.client.annotation.Client;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import net.shyshkin.study.micronaut.book.proxy.IsbnThirteen;
import net.shyshkin.study.micronaut.book.proxy.NumberResourceProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

@Singleton
@Named("mainBookService")
public class BookServiceImpl implements BookService {

    @Inject
    @Client
    NumberResourceProxy proxy;

    private final static Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

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
