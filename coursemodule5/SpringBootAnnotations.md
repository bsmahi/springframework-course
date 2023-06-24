# Spring Boot Annotations

Spring Boot makes significant use of annotations to facilitate application configuration and development. Here are some examples of regularly used Spring Boot annotations:

Spring Boot annotations are typically found in the following packages:

 * org.springframework.boot.autoconfigure
 * org.springframework.boot.autoconfigure.condition

## 1. @SpringBootApplication

The most crucial aspect of Spring Boot is its _autoconfiguration_. Spring Boot does autoconfiguration by scanning the components, which means it searches all classes with the **@Component** annotation.  It also searches the classes for the **@Configuration** annotation in order to initialize additional beans.

The annotation **@SpringBootApplication** allows the application to accomplish all of these things.

The **@SpringBootApplication** annotation on a Java class is considered the primary class of Spring Boot, and the application begins with this class.

As a result, adding the **@SpringBootApplication** annotation is equal to utilizing the **@Configuration**, **@ComponentScan**, and **@EnableAutoConfiguration** annotations with their default values.

> **@SpringBootApplication = @Configuration + @EnableAutoConfiguration + @ComponentScan**

That means, **@SpringBootApplication** allows the following features:

* @SpringBootConfiguration Annotation: Used to register the extra beans
* @ComponentScan Annotation: Used to enable the Component Scanning for all classes annotated with @Component
* @EnableAutoConfiguration Annotation: Used to enable the autoconfiguration feature

## Example of SpringBootApplication

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CourseApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseApplication.class, args);
    }

}
```

## 2. @EnableAutoConfiguration

**@EnableAutoConfiguration** activates the autoconfiguration capability of the Spring Boot application. That is, Spring Boot will seek for all autoconfiguration beans in its classpath and apply them automatically.

If we have a jar `tomcat-embedded.jar` in our application's classpath, it will automatically configure the TomcatServletWebServerFactory class.

@EnableAutoConfiguration is used in conjunction with the @Configuration annotation.

## Example of EnableAutoConfiguration

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@EnableAutoConfiguration
public class CourseApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseApplication.class, args);
    }
}
```

## 3. @SpringBootConfiguration

The @SpringBootConfiguration annotation is used to classes that offer Spring Boot Application settings. As a result, this annotation informs Spring Boot that this class contains application configuration.

Because the Spring @Configuration annotation is also used for annotating classes as configuration classes, @SpringBootConfiguration is seen as an alternate choice to the @Configuration annotation.

Spring Boot apps should have just one class annotated with @SpringBootConfiguration, and they will inherit it from @SpringBootApplication.

@SpringBootConfiguration annotation is very useful for unit and integration tests.

## Example of @SpringBootConfiguration
```java
@SpringBootConfiguration
public class CourseApplication {

    public static void main(String[] args) {
       SpringApplication.run(CourseApplication.class, args);
    }

    @Bean
    public CourseService courseService() {
        return new CourseServiceImpl();
    }
}
```

## 4. @ImportAutoConfiguration

The annotation @ImportAutoConfiguration is used. The supplied autoconfiguration classes are imported and applied using Spring Boot.

The annotations @EnableAutoConfiguration and @ImportAutoConfiguration have several similarities, however, there is one major difference:

* The @EnableAutoConfiguration annotation is configuring all beans identified in the application's classpath during Scanning.
* @ImportAutoConfiguration solely takes into account the configuration classes specified in the annotation.
* When we don't need to activate the default autoconfiguration, we utilize @ImportAutoConfiguration.

## Example of @ImportAutoConfiguration

```java
// In below example, com.learnspring.controllers is the path where my controller classes exist
@ComponentScan("com.learnspring.controllers")
@ImportAutoConfiguration({WebMvcAutoConfiguration.class
    ,DispatcherServletAutoConfiguration.class
    ,EmbeddedServletContainerAutoConfiguration.class
    ,ServerPropertiesAutoConfiguration.class
    ,HttpMessageConvertersAutoConfiguration.class})
public class CourseApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(CourseApplication.class, args);
    }
}
```

## 5. @AutoConfigureOrder, @AutoConfigureBefore, and @AutoConfigureAfter

If we want our configurations to be applied in a specified sequence, we may use the following annotations:
* **@AutoConfigureOrder:** Annotation is used to sort some auto-configuration classes that have no direct knowledge of one another
* **@AutoConfigureBefore:** When you want to apply your configuration but before the specified configuration, you use annotation
* **@AutoConfigureAfter:** When you want to apply your configuration only after the supplied configuration has been applied, use annotation

**UseCase:** If you provide web-specific configurations in your class, and you want your configuration to be applied after the WebMvcAutoConfiguration, use this annotation.

## 6. Condition Annotations

It is possible to establish the requirements for registering the bean in Spring Boot. Those beans will only be registered if the criterion is met.

Auto-configuration classes in Spring Boot can have one or more **@Conditional** annotations. Let's look at the various conditional annotations in Spring Boot:

### 6.1 @ConditionalOnBean and @ConditionalOnMissingBean

The annotations `@ConditionalOnBean` and `@ConditionalOnMissingBean` act based on the existence or absence of a given bean.

* It contains a **value** attribute that defines the type or name of the bean
* It only has one search property, which allows us to limit the hierarchy of **ApplicationContext** that is examined while looking for beans

## Example of @ConditionalOnBean and @ConditionalOnMissingBean

_Using **@ConditionalOnBean** on @Bean method,_

For example, **@ConditionalOnBean(name = "emailNotificationProvider")** will load the bean **emailNotification** if the Bean with the name **"emailNotificationProvider"** exists in the container.

and if a bean of type EmailNotificationService is not already defined in the application context.

```java
@Bean("emailNotification")
@ConditionalOnMissingBean(EmailNotificationService.class)
@ConditionalOnBean(name = "emailNotificationProvider")
public EmailNotificationService emailNotificationService() {
    return new EmailNotificationService();
}
```
_Using **@ConditionalOnBean** on @Service class_

For example, **@ConditionalOnBean(name = "smsNotificationProvider")**, when the Bean of name **"smsNotificationProvider"** exists in the container, the bean smsNotification will be loaded.
```java
@Service("smsNotification")
@ConditionalOnBean(name = "smsNotificationProvider")
public class SMSNotificationService {

    public SMSNotificationService() {
        System.out.println("SMSNotificationService Constructor");
    }
}
```

### 6.2 @ConditionalOnClass and @ConditionalOnMissingClass

The annotations **@ConditionalOnClass** and **@ConditionalOnMissingClass** allow settings to be loaded based on the existence or absence of specified classes.

_Using @ConditionalOnClass_

The annotation **@ConditionalOnClass** is used to activate or disable a configuration class based on the existence of particular classes in the classpath. It is typically used in auto-configuration classes to guarantee that particular configurations are produced only when the necessary dependencies/classes are accessible.

```java
@Configuration
@ConditionalOnClass(SampleDependency.class)
public class SampleAutoConfiguration {
    // ...
}
```
In the example above, the **SampleAutoConfiguration** class will only be loaded if the **SampleDependency** class is found.

_Using @ConditionalOnMissingClass_

As the name implies, `@ConditionalOnMissingClass` is the inverse of `@ConditionalOnClass`. When a certain class is not present, it enables a configuration or function. This is handy when you need to give a default or fallback configuration if certain dependencies are missing.

```java
@Configuration
@ConditionalOnMissingClass("com.learnspringboot.SampleDependency")
public class FallbackConfiguration {
    //...
}
```

In the example above, the `FallbackConfiguration` class will only be loaded if the `com.learnspringboot.SampleDependency` class is not present or found in the classpath.

## 7. @ConditionalOnNotWebApplication and @ConditionalOnWebApplication

The annotations `@ConditionalOnNotWebApplication` and `@ConditionalOnWebApplication` allow settings to be loaded based on whether the application is a Web Application or not.

Spring Boot web applications must fulfil one or more of the following requirements:

* Web Application uses a Spring _WebApplicationContext_.
* Web Application defines a _session_ scope.
* Web Application has a _StandardServletEnvironment_.