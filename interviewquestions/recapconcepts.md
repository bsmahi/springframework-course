
* Dependency Injection (DI):
    * Dependency Injection is a design pattern where the dependencies of a class are provided externally rather than created internally.
    * In our example, the BookService class might depend on a BookRepository to retrieve books.
* IoC Container:
    * The IoC container manages the instantiation and lifecycle of objects.
    * Spring's ApplicationContext serves as the IoC container.
* Beans:
    * In our example, BookService and BookRepository are beans managed by the Spring IoC container.
* XML Configuration (Optional):
    * We can configure beans in an XML file, defining their dependencies and properties.
    * Example XML configuration:
  ```xml
  <bean id="bookRepository" class="com.learnspring.BookRepositoryImpl"/>
  <bean id="bookService" class="com.example.BookServiceImpl">
   <property name="bookRepository" ref="bookRepository"/>
  </bean>
  ```
* Annotation-based Configuration:
    * We can use annotations like `@Component`, @Autowired, etc., to configure beans.
    * Example annotation-based configuration:
```java
   @Repository
   public class BookRepositoryImpl implements BookRepository { 
     // Implementation 
   }
  
   @Service
   public class BookServiceImpl implements BookService { 
   
     @Autowired
     private BookRepository bookRepository;
  
    // Other methods
     }
   }
```

* Spring Boot:
    * Spring Boot simplifies the setup and configuration of Spring applications.
    * By adding the @SpringBootApplication annotation, many configurations are auto-generated.
* Dependency Injection Types:
    * Constructor Injection:
```java
@Service
public class BookServiceImpl implements BookService {
 private final BookRepository bookRepository;

 @Autowired
 public BookServiceImpl(BookRepository bookRepository) { 
   this.bookRepository = bookRepository; 
 } 
  // Other methods

}
```
* Setter Injection and Field Injection can be similarly demonstrated.
  
* **Aspect-Oriented Programming (AOP):** SpEL
    * We can use AOP to add logging to our application without cluttering the business logic.

Example:
```java
@Aspect
@Component
public class LoggingAspect {
 
   @Before("execution(* com.learning.BookService.*(..))")
   public void logBefore(JoinPoint joinPoint) {
   
   System.out.println("Before method: " + joinPoint.getSignature().getName());
 }
}
```
 * **Spring MVC (Model-View-Controller)**:
    * Spring MVC helps build web applications.
    * We can define controllers, views, and models to handle web requests.

* **Spring Data**:
    * Spring Data simplifies data access.
      
   Example:
  ```java
   public interface BookRepository extends JpaRepository<Book, Long> { // Custom methods }
  ```
