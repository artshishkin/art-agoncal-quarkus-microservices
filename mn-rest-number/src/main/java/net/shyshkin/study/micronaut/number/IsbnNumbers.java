package net.shyshkin.study.micronaut.number;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.Objects;

@Introspected
@Schema(description = "Several ISBN numbers for books")
public class IsbnNumbers {

    @Schema(description = "ISBN number with 10 digits", required = true)
    @JsonProperty("isbn_10")
    private String isbn10;

    @Schema(description = "ISBN number with 13 digits", required = true)
    @JsonProperty("isbn_13")
    private String isbn13;

    @JsonIgnore
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
