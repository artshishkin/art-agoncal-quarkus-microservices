package net.shyshkin.study.quarkus.microservices.number;

import javax.enterprise.context.ApplicationScoped;
import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;

@ApplicationScoped
public class RandomIsbnGenerationService implements IsbnGenerationService {

    @Override
    public IsbnNumbers generateIsbnNumbers() {

        var isbnNumbers = new IsbnNumbers();
        isbnNumbers.setIsbn13("13-" + ThreadLocalRandom.current().nextInt(100_000_000));
        isbnNumbers.setIsbn10("10-" + ThreadLocalRandom.current().nextInt(100_000));
        isbnNumbers.setGenerationDate(Instant.now());
        return isbnNumbers;
    }
}
