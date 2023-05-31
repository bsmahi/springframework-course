# BeanNameAware

BeanNameAware belong to `org.springframework.beans.factory.Aware` root marker interface. This uses setter injection to get object during the application context startup.

The `Aware` interface employs design patterns such as `callback, listener, and observer`. It indicates that the bean is eligible to receive notifications from the Spring container via the callback methods.

`BeanNameAware` makes the object aware of the bean name defined in the container.

Let's create a class that implements BeanNameAware interface, as shown below.

# 1. Let's have a look at an example using **Annotation Based**

## Step 1: Create the following packages under the package com.springcourse.learnspring

1. _beanlifecycle_
2. under _beanlifecycle_ create sub package _beannameaware_

## Step 2: Create BeanNameAwareInterfaceExample class by implementing `BeanNameAware`
```java
package com.springcourse.learnspring.beanlifecycle.beannameaware;

import org.springframework.beans.factory.BeanNameAware;

// In this class, we have implemented BeanNameAware interface and accessed the name of the bean using.
public class BeanNameAwareInterfaceExample implements BeanNameAware {

    @Override
    public void setBeanName(String name) {
        System.out.println("setBeanName called");
        System.out.println("Bean name : " + name);
    }
}
```

The _name_ parameter represents the Spring container's registered bean id. In our solution, the bean name is simply shown.

## Step 3: Let's now add a bean of this type to a Spring configuration class

```java
package com.springcourse.learnspring.beanlifecycle.beannameaware;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanNameAwareConfig {

    @Bean(name = "myBeanNameAware")
    public BeanNameAwareInterfaceExample getBeanNameAwareInterfaceExample() {
        return new BeanNameAwareInterfaceExample();
    }
}
```

With the `@Bean(name = "myBeanNameAware")` line, we've explicitly assigned named our `BeanNameAwareInterfaceExample` class.

## Step 4: We can now launch the application context by running `BeanNameAwareUsingConfigTest` and retrieve the bean from it

```java
package com.springcourse.learnspring.beanlifecycle.beannameaware;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanNameAwareUsingConfigTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanNameAwareConfig.class); // (1)

        BeanNameAwareInterfaceExample contextBean = context.getBean(BeanNameAwareInterfaceExample.class); // (2)

        context.registerShutdownHook(); // (3)
    }
}
```
The setBeanName function returns `myBeanNameAware` as expected.

When we delete the `name = "..."` code from the `@Bean` annotation, the container gives the `getBeanNameAwareInterfaceExample()` method name to the bean. As a result, the output will be `getBeanNameAwareInterfaceExample`.

```log
20:05:09.570 [main] DEBUG org.springframework.context.annotation.AnnotationConfigApplicationContext -- Refreshing org.springframework.context.annotation.AnnotationConfigApplicationContext@28f67ac7 --> (1)
20:05:09.590 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory -- Creating shared instance of singleton bean 'org.springframework.context.annotation.internalConfigurationAnnotationProcessor'
20:05:09.799 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory -- Creating shared instance of singleton bean 'org.springframework.context.event.internalEventListenerProcessor'
20:05:09.803 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory -- Creating shared instance of singleton bean 'org.springframework.context.event.internalEventListenerFactory'
20:05:09.805 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory -- Creating shared instance of singleton bean 'org.springframework.context.annotation.internalAutowiredAnnotationProcessor'
20:05:09.807 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory -- Creating shared instance of singleton bean 'org.springframework.context.annotation.internalCommonAnnotationProcessor'
20:05:09.819 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory -- Creating shared instance of singleton bean 'beanNameAwareConfig'
20:05:09.825 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory -- Creating shared instance of singleton bean 'myBeanNameAware'
setBeanName called
Bean name : myBeanNameAware --> (2)
20:05:09.887 [SpringContextShutdownHook] DEBUG org.springframework.context.annotation.AnnotationConfigApplicationContext -- Closing org.springframework.context.annotation.AnnotationConfigApplicationContext@28f67ac7, --> (3)
```
# 2. Let's have a look at an example using **Xml Based**

## Step 1 & Step 2 follow the same from annotation based.

## Step 3: Let's create `beans.xml` file and placed under `/src/main/resources/` and define a bean

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:p="http://www.springframework.org/schema/p"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
        http://www.springframework.org/schema/context
  		http://www.springframework.org/schema/context/spring-context-3.2.xsd">

    <bean id="beanNameAwareInterfaceExample" class="com.springcourse.learnspring.beanlifecycle.beannameaware.BeanNameAwareInterfaceExample" />

</beans>

```

## Step 4: We can now launch the classpath application context by running `BeanNameAwareUsingXmlTest` and retrieve the bean from it

```java
package com.springcourse.learnspring.beanlifecycle.beannameaware;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanNameAwareUsingXmlTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml"); // (1)

        BeanNameAwareInterfaceExample beanNameAwareInterfaceImpl = (BeanNameAwareInterfaceExample) applicationContext.getBean("beanNameAwareInterfaceExample"); // (2)

        applicationContext.registerShutdownHook(); // (3)
    }
}
```

1. Read beans.xml file from classpath where file is available
2. Get the bean name `beanNameAwareInterfaceExample` defined in `beans.xml`
3. Closing the application context

```log
20:29:37.613 [main] DEBUG org.springframework.context.support.ClassPathXmlApplicationContext -- Refreshing org.springframework.context.support.ClassPathXmlApplicationContext@ea4a92b
20:29:37.836 [main] DEBUG org.springframework.beans.factory.xml.XmlBeanDefinitionReader -- Loaded 1 bean definitions from class path resource [beans.xml]
20:29:37.885 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory -- Creating shared instance of singleton bean 'beanNameAwareInterfaceExample'
setBeanName called
Bean name : beanNameAwareInterfaceExample
20:29:37.955 [SpringContextShutdownHook] DEBUG org.springframework.context.support.ClassPathXmlApplicationContext -- Closing org.springframework.context.support.ClassPathXmlApplicationContext@ea4a92b
```