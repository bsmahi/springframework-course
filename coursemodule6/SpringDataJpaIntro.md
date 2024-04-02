# Spring Data JPA

## Spring Data Jpa Using Java Annotation Based Configuration

1. Spring Data Jpa provides a solution to reduce a lot of boilerplate code which we used to write it.
2. It provides implementation methods for all the CRUD operations which we used to do it
3. We will get full implementation by extending JPARepository class to implement Data Access Object (DAO) layer/ Persistence Layer
4. It is library/framework that adds an extra layer of abstraction on the top of our JPA provider  for example Hibernate
5. It uses Hibernate as a default JPA provider

Discuss about **Spring Data Commons, and Spring Data JPA Repositories/interfaces**

Spring Data Commons is part of the Spring Data project that provides shared infrastructure across the Spring Data Projects, and it is technology-neutral repository interfaces as well as a metadata model for persisting Java classes/Entities

It contains the following interfaces

1. **Repository<T, ID extends Serializable>** interface which is marker interface, org.springframework.data.repository
2. **CrudRepository<T, ID extends Serializable>** interface provides CRUD operations for the managed entity
3. **PagingAndSortingRepository<T, ID extends Serializable>** interface is an extension of CrudRepository to provide additional methods to retrieve entities using the pagination and sorting abstraction.
4. **QueryDslPredicateExecutor** interface is not a “repository interface”. It declares the methods that are used to retrieve entities from the database by using QueryDsl Predicate objects.

The @Indexed annotation is specifically related to full-text search indexing, usually used in conjunction with Hibernate Search as explained earlier. It's applied to entity classes that you want to index for full-text search capabilities.

Spring Data Jpa comes with an additional features supporting for JPA data access layers.

Spring Data JPA project provides the following interfaces:

* **JpaRepository<T, ID extends Serializable>** interface is a JPA-specific repository interface that combines the methods declared by the common repository interfaces behind a single interface.
* **JpaSpecificationExecutor** interface is not a “repository interface”. It declares the methods that are used to retrieve entities from the database by using Specification objects that use the JPA criteria API.
