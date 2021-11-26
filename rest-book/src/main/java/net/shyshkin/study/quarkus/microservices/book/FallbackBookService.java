package net.shyshkin.study.quarkus.microservices.book;

import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;

@ApplicationScoped
public class FallbackBookService implements BookService {

    @Inject
    Logger logger;

    @Override
    public Book createABook(String title, String author, int yearOfPublication, String genre) {

        Book book = new Book();
        book.setIsbn13("Will be set later");
        book.setTitle(title);
        book.setAuthor(author);
        book.setYearOfPublication(yearOfPublication);
        book.setGenre(genre);
        book.setCreationDate(Instant.now());

        try {
            saveBookOnDisk(book);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        logger.warn("Book saved on disk: " + book);

        return book;
    }

    private void saveBookOnDisk(Book book) throws IOException {
        String bookJson = JsonbBuilder.create().toJson(book);
        Path tempFile = Files.createTempFile("book-", ".json");
        Files.writeString(tempFile, bookJson);
        logger.info("Created temp file :" + tempFile.toAbsolutePath());
    }
}
