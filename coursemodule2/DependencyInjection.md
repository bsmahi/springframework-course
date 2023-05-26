# Dependency Injection - DI

`Dependency Injection` is a design pattern used in software development to perform inversion of control (IoC). It enables you to decouple class dependencies, making your code more modular, testable, and manageable. Because of its strong support for DI, the Spring Framework is a popular choice for developing enterprise-level Java applications.

DI in Spring is generally accomplished through the usage of the Inversion of Control (IoC) container, which oversees object creation and wiring. The IoC container is in charge of object instantiation, dependency injection, and lifecycle management.

Spring provides various techniques for injecting dependencies:

1. **Constructor Injection:** Dependencies are injected through a constructor.
2. **Setter Injection:** Dependencies are injected through setter methods.
3. **Field Injection:** Dependencies are injected directly into fields (requires the @Autowired annotation).
4. **Method Injection:** Dependencies are injected into methods (requires the @Autowired annotation).

Let us have a look at how Dependency Injection works in the Spring Framework.

Suppose we have two classes, `PaymentService` and `DebitCardService`, where PaymentService depends on DebitCardService to accept the payments. We want to utilize Dependency Injection to inject the DebitCardService dependency into the PaymentService class.

First, let's define the DebitCardService class:

```java

public class DebitCardService {
    public void acceptAmount(String message) {
    // Code to accept the payment using debit card
    System.out.println("Accepting the Amount: " + message);
    }
}
```
