# Spring Core
Spring Core, commonly known as the Spring Framework's Core Container, is the Spring Framework's core module. It is the backbone of the whole Spring ecosystem, providing the necessary functionality for **Dependency Injection (DI)** and **Inversion Of Control(IoC)**.

`Dependency Injection (DI)` is the central notion of Spring Core. DI is a design paradigm that encourages `loose coupling` and `modularization` by allowing items to be connected together without establishing direct dependencies between them. DI is performed in Spring by using a container that maintains the lifespan and configuration of objects known as **Beans**.

Developers may design and configure beans in the Spring Core Container using 
 - XML configuration files
 - Java annotations
 - Java-based configuration classes. 

When developing and managing complex objects, this technique provides flexibility and simplicity of use. The container handles instantiating, configuring, and assembling beans, as well as resolving dependencies and managing their lifetime.

Spring Core, in addition to DI, has the following useful features:

1. **Inversion of responsibility (IoC):** Spring Core follows the IoC principle by transferring responsibility of object creation and lifecycle management from application code to the container. This method results in more modular, reusable, and testable programming.
2. **Bean Scopes:** Spring Core supports a variety of bean scopes, including **singleton, prototype, request, session, and others**. These scopes specify how bean instances are produced and shared inside an application.
3. **Property and Constructor Injection:** Spring Core allows developers to inject dependencies into beans via **constructor parameters or property setters**, allowing for more flexible and adaptable object development.
4. **Aspect-Oriented Programming (AOP) Integration:** Spring Core works smoothly with Aspect-Oriented Programming (AOP), allowing developers to apply cross-cutting concerns to their applications such as logging, transaction management, and security.
5. **Internationalization (I18N) Support:** Spring Core has functionality for internationalising applications, such as message and resource localization.

The Spring Core module serves as the foundation for other Spring modules, such as Spring MVC, Spring Data, and Spring Security, which build upon its features to provide comprehensive solutions for web development, data access, and security.

By leveraging the capabilities of Spring Core, developers can create modular, loosely coupled, and easily testable applications, while enjoying the benefits of a robust and mature framework.

### In the next chapters, we'll go through each functionality with code samples.