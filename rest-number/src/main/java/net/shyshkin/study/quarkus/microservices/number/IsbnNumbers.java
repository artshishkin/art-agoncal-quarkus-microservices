package net.shyshkin.study.quarkus.microservices.number;

import java.time.Instant;
import java.util.Objects;

public class IsbnNumbers {

    private String isbn10;
    private String isbn13;
    private Instant generationDate;

    public IsbnNumbers() {
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public Instant getGenerationDate() {
        return generationDate;
    }

    public void setGenerationDate(Instant generationDate) {
        this.generationDate = generationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IsbnNumbers that = (IsbnNumbers) o;
        return Objects.equals(isbn10, that.isbn10) && Objects.equals(isbn13, that.isbn13) && Objects.equals(generationDate, that.generationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn10, isbn13, generationDate);
    }

    @Override
    public String toString() {
        return "IsbnNumbers{" +
                "isbn10='" + isbn10 + '\'' +
                ", isbn13='" + isbn13 + '\'' +
                ", generationDate=" + generationDate +
                '}';
    }
}
