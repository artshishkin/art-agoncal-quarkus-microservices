package net.shyshkin.study.quarkus.microservices.book;

public interface BookService {

    Book createABook(String title,
                     String author,
                     int yearOfPublication,
                     String genre);

}
