# Architecture of Spring Framework

The primary goal behind the development of Spring Framework was to provide a one-stop shop where you could connect and use modules based on your application's needs. Spring's modularity is related to the architecture of Spring. The Spring framework has roughly 20 modules that are utilised depending on the nature of the application.

The Spring Framework architectural diagram is shown below. This layered design includes all the modules that a developer may need while creating an enterprise application. Furthermore, the developer is able to select or delete any modules that are required or are not required. Because of its modular nature, Spring Framework can simply be integrated with other frameworks.

![Image](../images/Spring-Overview.png "Overview of Spring")

**Source: Spring Framework Documentation**

Let's go over each architectural module in detail:

## 1. Core Container Layer
As you can see, all the modules are specified on top of the **Core container** 

* **Core:** It is the framework's core, and it contains important features like *Inversion of Control (IoC)* and *Dependency Injection (DI)* from the **spring-core** dependency.
* **Beans:** Provides Beanfactory, which is a sophisticated implementation of the [Factory Pattern](https://refactoring.guru/design-patterns/factory-method) from the **spring-beans** dependency.
* **Context:** It is based on **Core** and **Beans** and offers a means to access specified objects. The foundation of the Context module is the **ApplicationContext** interface, while the _spring-context-support_ package adds support for third-party interactions such as **caching**, **mailing**, and **template engines**. We'll be using **spring-context** dependency jar.
* **Expression Language:** Allows users to query and alter the object graph at runtime using the Spring Expression Language (SpEL). It uses **spring-expression** dependency jar.

## 2. Data Access/Integration Layer

This covers the modules used in an application to manage data access and transaction processing.

* **Java Database Connectivity (JDBC):** Provides a JDBC abstraction layer that avoids the requirement for JDBC coding to be separated when working with databases. Using **spring-jdbc** dependency we can achieve this feature.
* **Object Relation Mapping (ORM):** It is a layer of integration for major object-relational mapping APIs such as JPA, JDO, and Hibernate. Using **spring-orm** dependency we can achieve this feature.
* **Object XML Mapping (OXM):** It is an abstraction layer that provides support for Object/XML mapping technologies such as JAXB and XStream. Using **spring-oxm** dependency we can achieve this feature.
* **Java Message Service (JMS):** It is the Java Messaging Service module, which generates and consumes messages that connect directly with the Spring messaging module. Using **spring-jms** dependency we can achieve this feature. 
* **Transactions:** Provides transaction management for classes that have special interfaces and POJOs. Using **spring-tx** dependency we can achieve this feature.

## 3. Web Layer

The Web layer refers to Spring modules that power web-based functions.

* **Web:** It includes all the core web-oriented functionality, as well as an HTTP client and web-related Spring remoting components. Using **spring-web** dependency we can achieve this feature.
* **Servlet** Spring's module is a **Model-View-Controller (MVC)**. It is responsible for the implementation of web applications. Using **spring-webmvc** dependency we can achieve this feature.
* **Portlet** The Spring module is insists about implementing MVC in a portlet context. The functionality of Web-Portlet is identical to that of Spring's Web-Socket module. Using **spring-webmvc-portlet** dependency we can achieve this feature.
* **Struts** It is used to create a web applications based on servlet and JSP. Struts depend on the MVC (Model View Controller) framework.

## 4. Miscellaneous 

Along with **AOP**, there are numerous key modules such as **Aspects Instrumentation, Messaging, and Testing**.

* **AOP:** It implements cross-cutting concerns. It can alternatively understand as an interceptor which intercepts some processes like a method which is intercepted by Spring AOP during its execution for adding an extra functionality. Using **spring-aop** dependency we can achieve this feature.
* **Aspects:** The Spring module is an established and robust AOP framework. Spring AspectJ integration is feasible while using it. Using **spring-aspects** dependency we can achieve this feature.
* **Instrumentation:** It is the support for class instrumentation and class loader implementations for application servers. Using **spring-instrument** dependency we can achieve this feature.
* **Messaging:** Spring module does configuration registration of message objects for message consumption from message queues. This module will be used to create scalable and reliable applications. Using **spring-messaging** dependency we can achieve this feature.
* **Test:** This module assists classes in writing integration and unit tests. It makes use of frameworks like JUnit or TestNG.

## Conclusion

In a nutshell, 

1. In **Core**, we will be using features of **IoC Container, Events, Resources, i18n, Validation, Data Binding, Type Conversion, SpEL, AOP, and AOT.**
2. In **Data Access**, we will be using features of **Transactions, DAO Support, JDBC, R2DBC, O/R Mapping, and XML Marshalling**.
3. In **Web Servlet**, we will be using features of **Spring MVC, WebSocket, SockJS, and STOMP Messaging**.
4. In **Integration**, we will be using features of **REST Clients, JMS, JCA, JMX, Email, Tasks, Scheduling, Caching, and Observability**.
5. In **Testing**, we will be using features of **Mock Objects, TestContext Framework, Spring MVC Test, and WebTestClient**.
6. In **Languages**, we will be using different programming languages like **Java, Kotlin, Groovy, Dynamic Languages**.
