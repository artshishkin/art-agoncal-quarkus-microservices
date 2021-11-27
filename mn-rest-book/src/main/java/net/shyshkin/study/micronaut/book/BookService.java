package net.shyshkin.study.micronaut.book;

public interface BookService {

    Book createABook(String title,
                     String author,
                     int yearOfPublication,
                     String genre);

}
