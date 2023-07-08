# Spring Data Jpa with H2 DB

An example of utilising Spring Data JPA with an H2 database. Spring Data JPA makes it easy to interface with databases that adhere to the Java Persistence API (JPA) standard.

Firstly, we will kickstart the Spring Boot project. The simplest way to do that is by means of the Spring Initializr online application.First and foremost, we will launch the Spring Boot project. The Spring Initializr web tool is the simplest method to accomplish this.

As seen in the diagram below, add the following dependencies and click **`Generate`**, zip file will be downloaded along with the project structure:

* Spring Web
* SpringData Jpa
* H2 Database (or other database you are using)

<div align="center">
<img src="../images/SpringDataJpaProjectLaunch.png" alt="SpringDataJpa">
</div>


As next step, import the project in your IDE before we add our Classes in it. Finally, when you open your project setup, you should see the following dependencies in pom.xml:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
  <groupId>com.h2database</groupId>
  <artifactId>h2</artifactId>
  <scope>runtime</scope>
</dependency>
```

you may start writing code. Here's an example of how to use Spring Data JPA with an H2 database:

## Step 1: Create an entity class representing a table in your database. For example, let's create a `User` entity
In JPA, an entity represents a database table. To construct an entity, we must first create a Java class and mark it with the @Entity annotation. Each field in the class corresponds to a table column. The following code defines a Person object with three fields: id, name, and surname:
```java
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;

    public Person(String name, String email) {
        super();
        this.name = name;
        this.email = email;
    }

    // Constructors, getters, and setters
}
```

**@GeneratedValue** is a JPA annotation that specifies how the value for the primary key field is generated. @GeneratedValue's strategy property indicates the generation strategy to be used.

The **GenerationType.AUTO** strategy allows the persistence provider to select the best approach for the given database. This is important when you wish to utilise an identical piece of code across various databases, but each database generates primary keys differently.

> **_NOTE:_**  The Java Persistence API classes to use very depending on your Spring Boot versions. If using Spring Boot 2, you will need to `import javax.persistence` packages. On the other hand, Spring Boot 3 applications need to import the `jakarta.persistence` packages in the Entity Class.

## Step 2: Create a repository interface that extends JpaRepository
The repository is a layer between the database and the application that offers an interface for executing CRUD activities on the Person entity (create, read, update, delete). We need to construct an interface that extends the JpaRepository interface in order to create a repository. The JpaRepository interface has methods for basic CRUD activities as well as pagination.

```java
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByName(String name);
}
```
As you can see from the above code, we are including only an extra method findByName to search a Person by name.

## Step 3: Create a service class to handle business logic
The service layer is in charge of transaction management and business logic. To construct a service, we must first define an interface and then implement it. The code below establishes a PersonService Class that has a savePerson method for storing a Person object to the database:

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService   {
    @Autowired
    private PersonRepository personRepository;

    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Optional<Object> findById(long l) {
        return Optional.of(personRepository.findById(l));
    }

    public Optional<List<Person>> findByName(String name) {
        return Optional.of(personRepository.findByName(name));
    }
}
```
The PersonService class contains numerous methods for manipulating Person instances.

* savePerson: accepts a Person object as an input and saves it to the repository using the PersonRepository's save function.
* findAll: The findAll method of PersonRepository retrieves all Person objects from the repository.
* findById: The findById function of PersonRepository accepts a long number as an input and returns an Optional object holding the Person object with the matching ID.
* findByName: The PersonRepository's findByName function accepts a String value as an input and returns an Optional object containing a list of Person objects with the matching name.

> Returning an **Optional** allows the method's caller to check if a value exists before attempting to access it. If the value is null, this can prevent NullPointerExceptions from being raised. It makes the method's aim more obvious.

## Step 4: Create the controller
The controller is in charge of receiving incoming HTTP requests and responding appropriately.

This controller offers a number of endpoints for CRUD actions on Person objects. The @RestController annotation indicates that this class is a controller, while the @RequestMapping annotation gives the base route for the class's endpoints.

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persons")
public class PersonController {
  private final PersonService personService;

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @PostMapping
  public Person savePerson(@RequestBody Person person) {
    return personService.savePerson(person);
  }

  @GetMapping
  public List<Person> findAll() {
    return personService.findAll();
  }

  @GetMapping("/{id}")
  public Optional<Object> findById(@PathVariable long id) {
    return personService.findById(id);
  }

  @GetMapping("/name/{name}")
  public Optional<List<Person>> findByName(@PathVariable String name) {
    return personService.findByName(name);
  }
}
```

Each endpoint simply calls the PersonService class's appropriate method to conduct the requested operation and delivers the result to the client.

## Step 5: Creating data.sql and adding entries to the application.properties

1. We must create data.sql under (/src/main/resources/) along with the some insert statements
```roomsql
insert into person values('john', 'john@gmail.com');
insert into person values('steve', 'stevejobs@gmail.com');
```
2. Add the h2 db related properties in application.properties

```properties
# Enabling H2 Console
spring.h2.console.enabled=true
#Turn Statistics on
spring.jpa.properties.hibernate.generate_statistics=true
logging.level.org.hibernate.stat=debug
# Show all queries
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
logging.level.org.hibernate.type=trace
spring.datasource.url=jdbc:h2:mem:testdb
spring.data.jpa.repositories.bootstrap-mode=default
spring.jpa.defer-datasource-initialization=true
```

## Step 6: Running Main Application

Finally, we'll provide an Application Class that includes the @SpringBootApplication. We will demonstrate how to use the CommandLineRunner to run a basic test from within the Application Class. The CommandLineRunner bean may be used to run code that should be executed when the application starts.

```java
package com.springcourse.learnspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LearnSpringBootApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(PersonService personService) {
        return (args) -> {
            // save a couple of persons
            personService.savePerson(new Person("Jack", "Smith"));
            personService.savePerson(new Person("Joe", "Black"));
            personService.savePerson(new Person("Martin", "Bauer"));


            // fetch all persons
            LOGGER.info("Persons found with findAll():");
            LOGGER.info("-------------------------------");
            for (Person person : personService.findAll()) {
                log.info(person.toString());
            }
            LOGGER.info("");

            // fetch an individual person by ID
            personService.findById(1L)
                    .ifPresent(person -> {
                        LOGGER.info("Person found with findById(1L):");
                        LOGGER.info("--------------------------------");
                        LOGGER.info(person.toString());
                        LOGGER.info("");
                    });


            LOGGER.info("Person found with findByName:");
            LOGGER.info("--------------------------------------------");

            Optional<List<Person>> people = personService.findByName("Steve");
            if (people.isPresent()) {
                people.get().forEach(person -> LOGGER.info(people.toString()));
            }


            LOGGER.info("");
        };
    }
}
```

Then, you can run your **LearnSpringBootApplication** by simply right-clicking on your main Class from the IDE.

Alternatively, you can also run it as follows:

```renderscript
mvn clean install spring-boot:run
```