package net.shyshkin.study.quarkus.microservices.book;

import net.shyshkin.study.quarkus.microservices.book.proxy.IsbnThirteen;
import net.shyshkin.study.quarkus.microservices.book.proxy.NumberResourceProxy;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.Instant;

@ApplicationScoped
public class BookServiceImpl implements BookService {

    @Inject
    @RestClient
    NumberResourceProxy proxy;

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

        return book;
    }
}
