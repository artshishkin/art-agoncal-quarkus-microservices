package net.shyshkin.study.micronaut.book;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

@Introspected
@Schema(description = "This is a book")
public class Book {

    @JsonProperty("isbn_13")
    @Schema(required = true)
    private String isbn13;

    @Schema(required = true)
    private String title;

    @Schema(required = true)
    private String author;

    @JsonProperty("year_of_publication")
    @Schema(required = true)
    private int yearOfPublication;

    @Schema(required = true)
    private String genre;

    @JsonProperty("creation_date")
    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "UTC")
    @Schema(implementation = String.class, format = "date")
    private Instant creationDate;

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn13='" + isbn13 + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", yearOfPublication=" + yearOfPublication +
                ", genre='" + genre + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
