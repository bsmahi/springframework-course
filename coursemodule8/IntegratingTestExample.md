# Integration Testing with the Http Methods

We would want to launch the whole spring environment while developing an integration test for a rest service.

* @SpringBootTest(classes = LearnSpringBootApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) : Launch the entire Spring Boot Application on a Random Port

* @LocalServerPort private int port;: Autowired the random port into the variable so that we can use it create the url.

* createURLWithPort(String uri) : Utility method to create the url given an uri. It appends the port.

* HttpEntity<String> entity = new HttpEntity<String>(null, headers);: We use entity so that we have the flexibility of adding in request headers in the future.

* restTemplate.exchange(createURLWithPort("/books"),HttpMethod.GET, entity, String.class): Fire a GET request to specify uri and get the response as a String.

* JSONAssert.assertEquals(expected, response.getBody(), false) : Assert that the response contains expected fields.

TestRestTemplate is an HTTP client that allows you to quickly and easily test restful web services.

To make TestRestTemplate available in the context, we may use the @SpringBootTest annotation. We can then auto-wire it and inject it into our test class.

Without further ado, here's a practical example of how to utilise TestRestTemplate:

## Step 1: Create a class BookControllerIT (integration test should be suffix with *IT) and all method for each of the operation

```java
package com.springcourse.learnspringboot.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort // injects the HTTP server port that was generated at runtime
    private int randomServerPort;

    @Test
    void shouldReturnAllBooks() {
        Book[] books = restTemplate.getForObject("http://localhost:" + randomServerPort + "/books", Book[].class);

        assertEquals(3, books.length);
        assertEquals("ISBN1", books[0].getIsbn());
    }

    @Test
    void shouldReturnBookById() {
        Book book = restTemplate.getForObject("http://localhost:" + randomServerPort + "/books/1003", Book.class);

        assertEquals(1003, book.getId());
        assertEquals("ISBN3", book.getIsbn());
    }

    @Test
    void shouldCreateABook() {
        Book bookOne = new Book(1004L, "Book4", "Author4", "ISBN4");
        ResponseEntity<Book> response = restTemplate.postForEntity("http://localhost:" + randomServerPort + "/books", bookOne, Book.class);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Book4", Objects.requireNonNull(response.getBody()).getTitle());
        assertEquals("Author4", response.getBody().getAuthor());
    }
}

```
In a nutshell, `TestRestTemplate` provides a set of built-in methods to send HTTP web requests.

* We used _getForObject()_ to perform GET requests in order to test getAll() and getById() endpoints

* _postForEntity()_ allows to test POSTing a new Book object. We used exchange() to test the PUT method
