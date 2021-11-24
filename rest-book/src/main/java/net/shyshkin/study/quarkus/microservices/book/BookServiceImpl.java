package net.shyshkin.study.quarkus.microservices.book;

import javax.enterprise.context.ApplicationScoped;
import java.time.Instant;

@ApplicationScoped
public class BookServiceImpl implements BookService {

    @Override
    public Book createABook(String title, String author, int yearOfPublication, String genre) {

        Book book = new Book();
        book.setIsbn13("We need to get ISBN from external service");
        book.setTitle(title);
        book.setAuthor(author);
        book.setYearOfPublication(yearOfPublication);
        book.setGenre(genre);
        book.setCreationDate(Instant.now());

        return book;
    }
}
