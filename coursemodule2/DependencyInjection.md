# Dependency Injection - DI

`Dependency Injection` is a design pattern used in software development to perform inversion of control (IoC). It enables you to decouple class dependencies, making your code more modular, testable, and manageable. Because of its strong support for DI, the Spring Framework is a popular choice for developing enterprise-level Java applications.

DI in Spring is generally accomplished through the usage of the Inversion of Control (IoC) container, which oversees object creation and wiring. The IoC container is in charge of object instantiation, dependency injection, and lifecycle management.

Spring provides various techniques for injecting dependencies:

1. **Constructor Injection:** Dependencies are injected through a constructor.
2. **Setter Injection:** Dependencies are injected through setter methods.
3. **Field Injection:** Dependencies are injected directly into fields (requires the @Autowired annotation).
4. **Method Injection:** Dependencies are injected into methods (requires the @Autowired annotation).

## 1. Pre-requisite

## [Local Development Setup in IDE's](../LocalDevelopmentSteps.md)

#### Next, Let us have a look at how Dependency Injection works in the Spring Framework using various techniques.

## 2. Constructor-Based Injection (Recommended)

Constructor-based injection is one of the dependency injection techniques supported by the Spring framework. In this approach, dependencies are provided to a class through its constructor. Spring's dependency injection container is responsible for instantiating the class and injecting the required dependencies.

Here's an example that demonstrates constructor-based injection in Spring:

## 2.1 Create the `dependencyinjection` based folder within the `learn-spring` project and create sub folder called `constructorbased`
> com.springcourse.learnspring.dependencyinjection.constructorbased

And make the following classes to learn about constructor based dependency injection.

Suppose we have two classes, `UserService` and `EmailService`, where `UserService` depends on `EmailService` to send notifications. We want to utilize Dependency Injection to inject the `EmailService` dependency into the `UserService` class.

First, let's define the `EmailService` class:

```java
package com.springcourse.learnspring.dependencyinjection.constructorbased;

public class EmailService {
    public void sendEmail(String message) {
        // Code to send email
        System.out.println("Email sent: " + message);
    }
}
```
Next, we'll define the `UserService` class that has a dependency on `EmailService`:

```java
package com.springcourse.learnspring.dependencyinjection.constructorbased;

public class UserService {
    private final EmailService emailService;

    // Constructor Injection
    public UserService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void notifyUser(String username) {
        String message = "Hello, " + username + "! You have a new notification.";
        emailService.sendEmail(message);
    }
}

```
In the `UserService` class, we use constructor injection to inject the `EmailService` dependency. The `EmailService` object is passed as an argument to the constructor, and it is assigned to the emailService field.

Now, let's see how we configure and utilize Dependency Injection using the Spring Framework:

```java
package com.springcourse.learnspring.dependencyinjection.constructorbased;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public UserService userService() {
        return new UserService(emailService());
    }

    @Bean
    public EmailService emailService() {
        return new EmailService();
    }
}

```

In the configuration class (`AppConfig`), we use the `@Configuration` annotation to indicate that it provides bean definitions. We define two methods, `userService()` and `emailService()`, and annotate them with `@Bean`. These methods create and configure the beans for `UserService` and `EmailService`, respectively.

To utilize Dependency Injection, we can create an instance of the `ApplicationContext` and retrieve the `UserService` bean from it:

```java
package com.springcourse.learnspring.dependencyinjection.constructorbased;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConstructorBasedInjectionApplication {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        userService.notifyUser("SteveJobs - ConstructorBased");
    }
}

```

In the `ConstructorBasedInjectionApplication` class, we create an instance of `AnnotationConfigApplicationContext`, passing `AppConfig.class` as the configuration class. Then, we retrieve the `UserService` bean from the context using the `getBean()` method. Finally, we call the `notifyUser()` method on the `UserService` instance.

If you run `ConstructorBasedInjectionApplication` main class, `notifyUser()` is called, the `EmailService` dependency is automatically injected into the `UserService` instance through the constructor. The `EmailService` bean is created and managed by the Spring IoC container, and we will get the following output. 

```log 
12:10:50.762 [main] DEBUG org.springframework.context.annotation.AnnotationConfigApplicationContext -- Refreshing org.springframework.context.annotation.AnnotationConfigApplicationContext@28f67ac7
12:10:50.790 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory -- Creating shared instance of singleton bean 'org.springframework.context.annotation.internalConfigurationAnnotationProcessor'
12:10:51.023 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory -- Creating shared instance of singleton bean 'org.springframework.context.event.internalEventListenerProcessor'
12:10:51.027 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory -- Creating shared instance of singleton bean 'org.springframework.context.event.internalEventListenerFactory'
12:10:51.029 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory -- Creating shared instance of singleton bean 'org.springframework.context.annotation.internalAutowiredAnnotationProcessor'
12:10:51.031 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory -- Creating shared instance of singleton bean 'org.springframework.context.annotation.internalCommonAnnotationProcessor'
12:10:51.043 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory -- Creating shared instance of singleton bean 'appConfig'
12:10:51.050 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory -- Creating shared instance of singleton bean 'userService'
12:10:51.055 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory -- Creating shared instance of singleton bean 'emailService'
Email sent: Hello, SteveJobs - ConstructorBased! You have a new notification.
```
The above example shows how Dependency Injection works in the Spring Framework via `constructor injection`.

## 3. Setter-Based Injection

In Spring, setter-based injection is a type of dependency injection where dependencies are injected into a class using setter methods. This is achieved by annotating the setter methods with appropriate annotations. Here's an example to illustrate setter-based injection in Spring using annotations:

Here are the steps to get the `setter-based injection` working.

## 3.1 Create sub folder called `setterbased` inside `dependencyinjection` folder
> com.springcourse.learnspring.dependencyinjection.setterbased

Suppose we have two classes, `UserService` and `EmailService`, where `UserService` depends on `EmailService` to send notifications. We want to utilize Dependency Injection to inject the `EmailService` dependency into the `UserService` class.

First, let's define the `EmailService` class:

```java
package com.springcourse.learnspring.dependencyinjection.setterbased;

public class EmailService {
    public void sendEmail(String message) {
        // Code to send email
        System.out.println("Email sent: " + message);
    }
}
```
Next, we'll define the `UserService` class that has a dependency on `EmailService`:

```java
package com.springcourse.learnspring.dependencyinjection.setterbased;

public class UserService {
    private EmailService emailService;

    // Setter method
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void notifyUser(String username) {
        String message = "Hello, " + username + "! You have a new notification.";
        emailService.sendEmail(message);
    }
}
```

Now, let's see how we configure and utilize Dependency Injection using the Spring Framework:

```java
package com.springcourse.learnspring.dependencyinjection.setterbased;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public com.springcourse.learnspring.dependencyinjection.setterbased.UserService userService() {
        UserService service = new UserService();
        // Setter Injection
        service.setEmailService(emailService());

        return service;
    }

    @Bean
    public com.springcourse.learnspring.dependencyinjection.setterbased.EmailService emailService() {
        return new EmailService();
    }
}


```

In the configuration class (`AppConfig`), we use the `@Configuration` annotation to indicate that it provides bean definitions. We define two methods, `userService()` and `emailService()`, and annotate them with `@Bean`. These methods create and configure the beans for `UserService` and `EmailService`, respectively.

To utilize Dependency Injection, we can create an instance of the `ApplicationContext` and retrieve the `UserService` bean from it:

```java
package com.springcourse.learnspring.dependencyinjection.setterbased;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SetterBasedApplication {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        userService.notifyUser("Steve Jobs - SetterBased");
    }
}


```
In the `ConstructorBasedInjectionApplication` class, we create an instance of `AnnotationConfigApplicationContext`, passing `AppConfig.class` as the configuration class. Then, we retrieve the `UserService` bean from the context using the `getBean()` method. Finally, we call the `notifyUser()` method on the `UserService` instance.

If you run `SetterBasedInjectionApplication` main class, `notifyUser()` is called, the `EmailService` dependency is automatically injected into the `UserService` instance through the constructor. The `EmailService` bean is created and managed by the Spring IoC container, and we will get the following output. 

```log
13:49:31.376 [main] DEBUG org.springframework.context.annotation.AnnotationConfigApplicationContext -- Refreshing org.springframework.context.annotation.AnnotationConfigApplicationContext@28f67ac7
13:49:31.403 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory -- Creating shared instance of singleton bean 'org.springframework.context.annotation.internalConfigurationAnnotationProcessor'
13:49:31.635 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory -- Creating shared instance of singleton bean 'org.springframework.context.event.internalEventListenerProcessor'
13:49:31.637 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory -- Creating shared instance of singleton bean 'org.springframework.context.event.internalEventListenerFactory'
13:49:31.639 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory -- Creating shared instance of singleton bean 'org.springframework.context.annotation.internalAutowiredAnnotationProcessor'
13:49:31.641 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory -- Creating shared instance of singleton bean 'org.springframework.context.annotation.internalCommonAnnotationProcessor'
13:49:31.654 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory -- Creating shared instance of singleton bean 'appConfig'
13:49:31.661 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory -- Creating shared instance of singleton bean 'userService'
13:49:31.666 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory -- Creating shared instance of singleton bean 'emailService'
Email sent: Hello, Steve Jobs - SetterBased! You have a new notification.
```

The above example shows how Dependency Injection works in the Spring Framework via `setter injection`.

## 4. Field Injection

Field injection is a type of dependency injection in the Spring framework in which dependencies are injected directly into class fields. This method lets you annotate the fields with the `@Autowired` annotation to tell Spring to wire the dependencies for you. Here's an example of Spring field injection:

In constructor based injection package, replace the following in `UserService` with, we will use field injection by annotating with `@Autowired`.

Similar to Constructor-based Dependency Injection, here the Autowiring takes place by means of field `emailService`  The bean object created for EmailService class will be Autowired & injected via the field, ‘emailService’ in UserService class.

```java
    // Constructor Injection
    public UserService(EmailService emailService) {
        this.emailService = emailService;
    }
    
```
For `Field Injection`, replace the above code with the following.

```java
    @Autowired
    private EmailService emailService;
```

```java
package com.springcourse.learnspring.dependencyinjection.constructorbased;

public class UserService {
    
    @Autowired
    private EmailService emailService;

    public void notifyUser(String username) {
        String message = "Hello, " + username + "! You have a new notification.";
        emailService.sendEmail(message);
    }
}
```
In the example above, the `UserService` class has a field named emailService that is annotated with `@Autowired`. This annotation tells Spring to inject an instance of `EmailService` into that field.

## Note
> As of Spring Framework 4.3, an `@Autowired` annotation on such a constructor is no longer necessary if the target bean defines only one constructor to begin with. However, if several constructors are available and there is no primary/default constructor, at least one of the constructors must be annotated with @Autowired in order to instruct the container which one to use. See the discussion on constructor resolution for details.

## 5. Method Injection

In Spring Framework, method injection can also be achieved using annotations. The @Autowired annotation is commonly used for method injection. Here's an example of method injection using annotations:

Similar to Setter-based Dependency Injection, here the Autowiring takes place by means of field `emailService`  The bean object created for EmailService class will be Autowired & injected via the field, ‘emailService’ in UserService class.


```java
package com.springcourse.learnspring.dependencyinjection.setterbased;

import org.springframework.beans.factory.annotation.Autowired;

public class UserService {
    private EmailService emailService;

    // Method Injection
    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void notifyUser(String username) {
        String message = "Hello, " + username + "! You have a new notification.";
        emailService.sendEmail(message);
    }
}
```
## Advantages of Dependency Injection

Dependency Injection (DI) in the Spring Framework provides various benefits that contribute to application flexibility, maintainability, and testability. Here are some main benefits of Spring Framework Dependency Injection:

1. Loose Coupling
2. Easy Component Integration
3. Testability
4. Configurability
5. Single Responsibility Principle (SRP)
6. Runtime Flexibility
7. Centralized Dependency Management
8. Encourages Best Practices

## Disadvantages of Dependency Injection

1. Increased Complexity
2. Runtime Errors
3. Performance Overhead
4. Increased Complexity of Testing
5. Configuration Management
6. Dependency Explosion
7. Tight Coupling with the Framework
8. Steeper Learning Curve

Despite these possible drawbacks, Dependency Injection is still frequently used and offers considerable advantages in terms of `modularity, maintainability, and testability`. Before selecting to utilise DI in any particular circumstance, it is critical to thoroughly assess the advantages and downsides and analyse the project's individual requirements.