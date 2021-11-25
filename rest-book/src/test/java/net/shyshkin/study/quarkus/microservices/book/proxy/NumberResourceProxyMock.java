package net.shyshkin.study.quarkus.microservices.book.proxy;

import io.quarkus.test.Mock;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Mock
@RestClient
public class NumberResourceProxyMock implements NumberResourceProxy {

    @Override
    public IsbnThirteen getIsbnNumbers() {
        var isbnThirteen = new IsbnThirteen();
        isbnThirteen.isbn13 = "13-123123123";
        return isbnThirteen;
    }

}