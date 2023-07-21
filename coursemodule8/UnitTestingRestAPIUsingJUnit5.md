# Unit Testing Rest API using JUnit 5

Now that we've completed the Spring Boot API implementation, let's look at how we can unit test it with JUnit 5 and Mockito.

## Test JPA Repository using @DataJpaTest

Let's start with BookRepository. To improve testing functionality for JPA repositories, Spring Boot includes the `@DataJpaTest` annotation.

By annotating the unit test class with @DataJpaTest, we can instruct Spring to automatically configure JPA repositories.

### Step 1: Create BookRepositoryTest class in the below location /src/test/java/com/springcourse/learnspringboot/book
```java
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

}   
```

As we can see, we autowired bookRepository without any further configuration.

By default, @DataJpaTest uses an embedded in-memory database under the hood.

> Please note that with JUnit 5, test classes and test methods are not required to be public.

### Step 2: Now, let’s create a test case for each method:

```java
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void shouldReturnAllBooksList() {
        // When
        List<Book> books = this.bookRepository.findAll();

        // Then
        assertEquals(3, books.size());
    }

    @Test
    void shouldReturnBookById() {
        // When
        Optional<Book> books = this.bookRepository.findById(1001L);

        // Then
        assertTrue(books.isPresent());
    }

    @Test
    void shouldCreateABook() {
        // Given
        Book bookOne = new Book();
        bookOne.setId(1004L);
        bookOne.setTitle("AWS Lambda");
        bookOne.setAuthor("Mahi");
        bookOne.setIsbn("ISBN4");

        // When
        Book saveBook = this.bookRepository.save(bookOne);
        List<Book> books = this.bookRepository.findAll();

        // Then
        assertNotNull(saveBook);
        assertEquals(4, books.size());

    }

    @Test
    void shouldDeleteABookById() {
        // When
        this.bookRepository.deleteById(1001L);
        Optional<Book> books = this.bookRepository.findById(1001L);

        // Then
        assertFalse(books.isPresent());
    }
}
```

As shown above, we used JUnit 5 assertions to verify that everything works as expected for each method. Here is the result screen

<img src="../images/BookRepoTestCase.png" alt="BookRepoTestcases">

## Test Service Layer using Mockito

Next, we are going to test our service BookService. Since it uses BookRepository as a dependency, we will need to mock it first.

Simply put, [Mockito](https://site.mockito.org/) is a powerful mocking framework that takes testing to the next level.

**Fortunately, JUnit 5 comes with a ready-to-use extension model that supports Mockito.**

To enable Mockito integration, we need to annotate our test class with the `@ExtendWith(MockitoExtension.class)` annotation:

### Step 1: Create BookServiceTest in the below location /src/test/java/com/springcourse/learnspringboot/book
```java
@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;
}
```

* _**@Mock**_ allows us to create and inject a mock of BookRepository

* **_@InjectMocks_** is used to create an instance of our service BookService so that we can test it

### Step 2: Now, let’s exemplify the use of Mockito and JUnit 5 to test our service:

```java
package com.springcourse.learnspringboot.book;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    public void testGetAllBooks() {
        // Given
        Book book1 = new Book(1004L, "Book4", "Author4", "ISBN4");

        // When
        when(bookRepository.findAll()).thenReturn(List.of(book1));
        List<Book> books = bookService.getAllBooks();

        // Then
        assertThat(books).hasSize(1);
        verify(this.bookRepository).findAll();

    }
}

```