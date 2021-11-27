package net.shyshkin.study.micronaut.book;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;

@Singleton
public class FallbackBookService implements BookService {

    private static final Logger logger = LoggerFactory.getLogger(FallbackBookService.class);

    @Inject
    ObjectMapper objectMapper;

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
        String bookJson = objectMapper.writeValueAsString(book);
        Path tempFile = Files.createTempFile("book-", ".json");
        Files.writeString(tempFile, bookJson);
        logger.info("Created temp file :" + tempFile.toAbsolutePath());
    }
}
