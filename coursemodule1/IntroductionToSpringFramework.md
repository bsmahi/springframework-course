# Why Spring

Before we get into any specific Spring talk, let's first establish why we need to utilise any framework at all. Reasons are:

- Spring has gained popularity in the Java world as a supplement to, or substitute for, **Enterprise JavaBeans (EJB)**. The EJB was unable to provide key critical capabilities necessary for constructing safe applications, including as transaction management and security. Also, creating an application with EJB was difficult; hence, Spring provided answers to all the EJB's complications.

- **Inversion of Control (IoC), Aspect-Oriented Programming (AOP), Plain Old Java Object (POJO), Dependency Injection (DI), and Transaction Management** are some of the innovative approaches used by the Spring framework.

# Overview

Spring Framework is a robust and extensively used Java-based framework for developing enterprise-class applications. It offers an extensive programming and configuration architecture for current Java-based modern applications. At its core, is a **_depedency injection container_** that **manages the classes you wrote and their dependencies for you**, hence understanding spring framework serves as the foundation for all other projects is crucial.

The Spring Framework has a number of features and modules that may be used alone or in tandem to build strong and versatile applications. Some of the key features of Spring Framework are:

### 1. Inversion of Control - IoC
> The Spring Framework provides a lightweight container that manages the creation and lifecycle of Java objects. It allows developers to decouple the components of their application and makes it easy to manage dependencies.
It entails handing over control of producing and instantiating spring beans to the Spring IOC container, with the developer's only responsibility being to configure the beans in the spring xml file or define in using annotation approach.

### 2. Aspect-Oriented Programming - AOP
> Spring's AOP module enables developers to create cross-cutting concerns such as **_logging, transaction management, and security_** in a modular and reusable way.

### 3. Spring MVC
> For developing web applications, Spring's MVC framework uses a **_model-view-controller design_**. It makes web application development easier by offering a set of abstractions for handling request and response processing.

### 4. Spring Data
> Spring Data is a Spring Framework module that provides a single and consistent programming style for data access. It allows access to data from a variety of sources, including **_relational databases (RDMS) (Structured Format)_**, **_NoSQL(Unstructured Format)_** databases, and other data storage.

### 5. Spring Security
> Spring Security is a Java-based application security framework. It provides web applications and APIs with **_authentication_**, **_authorisation_**, and other security features.

### 6. Spring Boot
> Spring Boot is a module that simplifies the development of standalone, production-grade Spring-based applications. It offers a collection of pre-configured dependencies as well as a runtime environment to decrease boilerplate code and boost developer productivity.

### 7. Spring Cloud
> Spring Cloud is a module that includes tools for developing and delivering cloud-native applications. It has functions including **_service discovery, configuration management, and distributed tracing_**.


Overall, the Spring Framework is a strong and adaptable framework that offers a wide range of functionality for developing enterprise-grade applications. It has a huge and active development community and offers substantial documentation and support.
