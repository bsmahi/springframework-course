<div align="center">
<img src="./images/AnnotationsLogo.png" alt="SpringLogo">
</div>

## What is Annotation?

`Annotations` are a type of metadata in Java that offer additional information on code elements such as classes, methods, fields, and arguments. They let you to correlate data or configuration details with these pieces, which tools, frameworks, and libraries can utilise at build time, runtime, or during runtime analysis.

Annotations are denoted by the `@ symbol` followed by the annotation name. They can be placed directly before the annotated element, such as:

```java
@AnnotationName
public class SampleClass {
    // Class implementation
}

```
Java provides a set of built-in annotations, such as `@Override, @Deprecated, and @SuppressWarnings`, which serve specific purposes and have well-defined meanings. Additionally, you can define your own custom annotations by creating an annotation interface.

Annotations can be handled at compile time by annotation processors, which might create code, validate data, or provide extra information. Reflection can be used at runtime to access and analyse annotations on classes, methods, and other items.

Annotations have become an essential aspect of Java programming, allowing frameworks such as `Spring`, `Hibernate`, and `JUnit` to provide strong capabilities and ease application settings. They let you express information and declarative behaviours directly in your code, hence increasing readability and maintainability.

For more information [click here](https://www.jitendrazaa.com/blog/java/meta-annotation-annotate-other-annotation/)

## List of Annotations used in Spring Framework & Spring Boot

Here is a list of some commonly used annotations in the Spring Framework along with their explanations:

| Annotations                                                | Description                                                                                                                                                                                                                                   |
|------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **@Autowired**                                             | This annotation is used to autowire dependencies in Spring. It can be applied to fields, constructors, or setter methods, and Spring will automatically inject the required dependency                                                        |
| **@Bean**                                                  | It is used to define a bean in Spring. Methods annotated with `@Bean`produce bean instances that will be managed by the Spring container                                                                                                      |
| **@Component**                                             | This annotation is used to indicate that a class is a Spring-managed component. It is a generic stereotype for any Spring-managed component                                                                                                   |
| **@Service**                                               | Annotation is used to mark a class as a service component and it is one of the specialization of `@Component`                                                                                                                                 |
| **@Repository**                                            | Annotation is used to mark a class as a repository component and it is one of the specialization of `@Component`                                                                                                                              |
| **@Configuration**                                         | This annotation is used to define a configuration class in Spring. It is typically used in conjunction with `@Bean` annotations to define and configure beans                                                                                 |
| **@Controller**                                            | It is used to mark a class as a Spring MVC controller. It is typically used in combination with the `@RequestMapping` annotation to handle HTTP requests                                                                                      |
| **@PathVariable**                                          | It is used to bind a method parameter to a URI template variable in Spring MVC. It extracts the value from the URL and assigns it to the annotated method parameter                                                                           |
| **@Qualifier**                                             | When multiple beans of the same type are available, this annotation is used along with `@Autowired` to specify which bean should be injected by using the bean's qualifier value                                                              |
| **@Repository**                                            | It is used to indicate that a class is a Spring repository component. It is typically used in the persistence layer of an application to perform database operations                                                                          |
| **@RequestBody**                                           | It is used to bind the HTTP request body to a method parameter in Spring MVC. It can be used to deserialize JSON or XML data sent in the request body to a Java object                                                                        |
| **@RequestParam**                                          | This annotation is used to bind a method parameter to a query parameter in Spring MVC. It extracts the value from the request's query string and assigns it to the annotated method parameter                                                 |
| **@ResponseBody**                                          | This annotation is used to indicate that the return value of a method should be serialized and sent as the response body in Spring MVC. It can be used to return JSON, XML, or any other format                                               |
| **@RequestMapping**                                        | This annotation is used to map HTTP requests to methods in Spring MVC controllers. It is used to define the URL path and HTTP method for handling incoming requests                                                                           |
| **@PathVariable**                                          | It is used to bind a method parameter to a URI template variable in Spring MVC. It extracts the value from the URL and assigns it to the annotated method parameter                                                                           |
| **@Transactional**                                         | It is used to mark a method or class as transactional in Spring. It defines the boundaries of a transaction and ensures that the data changes are either committed or rolled back in case of exceptions                                       |
| **@Value**                                                 | It is used to inject values into fields in Spring components. It can be used to inject simple values or to read values from properties files                                                                                                  |
| **@SpringBootApplication**                                 | his annotation is a combination of three annotations: `@Configuration, @EnableAutoConfiguration, and @ComponentScan`. It is used to mark the main class of a Spring Boot application                                                          |
| **@EnableAutoConfiguration**                               | It is used to enable Spring Boot's auto-configuration mechanism. It allows Spring Boot to automatically configure the application based on the classpath dependencies and other conditions                                                    |
| **@ComponentScan**                                         | This annotation is used to specify the base packages for component scanning in Spring Boot. It tells Spring Boot where to look for Spring components such as controllers, services, and repositories                                          |
| **@EnableJpaRepositories**                                 | It is used to enable JPA repositories in Spring Boot. It scans the specified packages for interfaces extending the JpaRepository interface and creates repository beans for them                                                              |
| **@EnableCaching**                                         | This annotation is used to enable caching support in Spring Boot. It activates the caching infrastructure and allows the use of annotations such as @Cacheable, @CachePut, and @CacheEvict for caching data                                   |
| **@RestController**                                        | It is used to mark a class as a RESTful controller in Spring Boot. It combines the @Controller and @ResponseBody annotations, indicating that the methods in the class will handle HTTP requests and return the response as the response body |
| **@GetMapping, @PostMapping, @PutMapping, @DeleteMapping** | These annotations are shortcuts for `@RequestMapping` with specific HTTP methods. They are used to handle GET, POST, PUT, and DELETE requests, respectively                                                                                   |
 