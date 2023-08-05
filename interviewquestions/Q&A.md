## 1. Difference between RestController and Controller?

Both `@RestController` and `@Controller` are used for constructing web applications and processing HTTP requests in the context of Spring Framework, especially in Spring MVC, but they have somewhat distinct functions.

**@Controller** 

To designate a class as a Spring MVC controller, use the `@Controller` annotation. Controllers are in charge of handling incoming HTTP requests, applying business logic, and delivering relevant views or answers. `@Controller` is often used to return a view, which might be an HTML page, a JSP, a Thymeleaf template, or any other view technology. To map certain URL patterns to controller methods, the methods within a `@Controller` class are often annotated with `@RequestMapping` or equivalent annotations.

```java
@Controller
public class HelloWorldController {
    @RequestMapping("/hello")
    public String sayHello() {
        return "helloworld"; // Returns the view named "hello"
    }
}
```
**@RestController**

The `@RestController` annotation is a specialized version of the `@Controller` annotation. It is mostly used to create RESTful web services. When you use `@RestController`, you're indicating that the class will be in charge of processing HTTP requests and directly delivering data as the response body, often in JSON or XML format. The methods of a `@RestController` class are annotated with `@ResponseBody` by default, which means that their return values are immediately serialised to the HTTP response body.

```java
@RestController
@RequestMapping("/api")
public class HelloWorldRestController {
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, world!"; // Returns the plain string as the response body
    }
}
```
In a nutshell,
* When you wish to return views or templates, use `@Controller`
* When creating RESTful web services that return data as the response body (e.g., JSON), use `@RestController`
