# BeanNameAware

BeanNameAware belong to `org.springframework.beans.factory.Aware` root marker interface. This uses setter injection to get object during the application context startup.

The `Aware` interface employs design patterns such as `callback, listener, and observer`. It indicates that the bean is eligible to receive notifications from the Spring container via the callback methods.

`BeanNameAware` makes the object aware of the bean name defined in the container.

Let's create a class that implements BeanNameAware interface, as shown below.

## 1. Consider the following **_Annotation Based_** example.

### Step 1: Create the following packages under the package com.springcourse.learnspring

1. _beanlifecycle_
2. under _beanlifecycle_ create sub package _beannameaware_

### Step 2: Create BeanNameAwareInterfaceExample class by implementing `BeanNameAware`
```java
package com.springcourse.learnspring.springcore.beanlifecycle.steps.beannameaware;

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

### Step 3: Let's now add a bean of this type to a Spring configuration class

```java
package com.springcourse.learnspring.springcore.beanlifecycle.steps.beannameaware;

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

### Step 4: We can now launch the application context by running `BeanNameAwareUsingConfigTest` and retrieve the bean from it

```java
package com.springcourse.learnspring.springcore.beanlifecycle.steps.beannameaware;

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
## 2. Let's have a look at a **Xml Based** example.

### Step 1 & Step 2 follow the same from annotation based.

### Step 3: Let's create `beans.xml` file and placed under `/src/main/resources/` and define a bean

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

    <bean id="beanNameAwareInterfaceExample" class="com.springcourse.learnspring.springcore.beanlifecycle.steps.beannameaware.BeanNameAwareInterfaceExample" />

</beans>

```

### Step 4: We can now launch the classpath application context by running `BeanNameAwareUsingXmlTest` and retrieve the bean from it

```java
package com.springcourse.learnspring.springcore.beanlifecycle.steps.beannameaware;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanNameAwareUsingXmlTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml"); // (1)

        BeanNameAwareInterfaceExample beanNameAwareInterfaceImpl = (BeanNameAwareInterfaceExample) applicationContext.getBean("beanNameAwareInterfaceExample"); // (2)

        applicationContext.registerShutdownHook(); // (3)
    }
}
```

1. Read the `beans.xml` file from classpath where file is available
2. Get the bean name `beanNameAwareInterfaceExample` defined in the `beans.xml`
3. Closing the application context

```log
20:29:37.613 [main] DEBUG org.springframework.context.support.ClassPathXmlApplicationContext -- Refreshing org.springframework.context.support.ClassPathXmlApplicationContext@ea4a92b
20:29:37.836 [main] DEBUG org.springframework.beans.factory.xml.XmlBeanDefinitionReader -- Loaded 1 bean definitions from class path resource [beans.xml]
20:29:37.885 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory -- Creating shared instance of singleton bean 'beanNameAwareInterfaceExample'
setBeanName called
Bean name : beanNameAwareInterfaceExample
20:29:37.955 [SpringContextShutdownHook] DEBUG org.springframework.context.support.ClassPathXmlApplicationContext -- Closing org.springframework.context.support.ClassPathXmlApplicationContext@ea4a92b
```

## InitializingBean and DisposableBean

`InitializingBean` interface has one method `afterPropertiesSet()` which is called after all the properties of a bean are set.

`DisposableBean` interface has one method `destroy()` which is called before the bean gets destroyed, or before it gets removed from the application context.

Let's understand this concept through examples

### Step 1: Let us write an AlbumService class which implements above interfaces

```java
package com.springcourse.learnspring.beanlifecycle.steps;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class AlbumService implements InitializingBean, DisposableBean {

    private Album album;

    public AlbumService() {
        System.out.println("AlbumService no-arg constructor");
    }

    @Override
    public void afterPropertiesSet() { // init-method
        if (!album.getAlbumName().equals("Paradise")) {
            album.setAlbumName("ConquestOfParadise");
        }
        System.out.println("AlbumService afterPropertiesSet method");
    }

    @Override
    public void destroy() { // destroy-method
        System.out.println("AlbumService destroy method - Closing Opened Resources");
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }


}

```

## Step 2: Create a class called `Album`

```java
package com.springcourse.learnspring.beanlifecycle.steps;

public class Album {

    private String albumName;

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }
}
```

## Step 3: Modify existing `beans.xml` file by adding the following entry

```xml
<bean id="album" class="com.springcourse.learnspring.beanlifecycle.steps.Album">
    <property name="albumName" value="Paradise Lost"/>
</bean>

<bean id="albumService" class="com.springcourse.learnspring.beanlifecycle.steps.AlbumService">
 <property name="album" ref="album"/>
</bean>
```

## Step 4: Create the BeanLifeCycleTest class as below for verifying 

```java
package com.springcourse.learnspring.beanlifecycle.steps;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanLifeCycleTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");

        // Load AlbumService Object
        AlbumService album = (AlbumService) applicationContext.getBean("albumService");
        System.out.println("Album Name : " + album.getAlbum().getAlbumName());

        applicationContext.registerShutdownHook();

    }
}
```
Run the `BeanLifeCycleTest` class to view the results shown below.

```log
07:33:19.396 [main] DEBUG org.springframework.context.support.ClassPathXmlApplicationContext -- Refreshing org.springframework.context.support.ClassPathXmlApplicationContext@ea4a92b
07:33:19.696 [main] DEBUG org.springframework.beans.factory.xml.XmlBeanDefinitionReader -- Loaded 7 bean definitions from class path resource [beans.xml]
07:33:19.731 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory -- Creating shared instance of singleton bean 'org.springframework.context.annotation.internalConfigurationAnnotationProcessor'
07:33:19.782 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory -- Creating shared instance of singleton bean 'org.springframework.context.event.internalEventListenerProcessor'
07:33:19.784 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory -- Creating shared instance of singleton bean 'org.springframework.context.event.internalEventListenerFactory'
07:33:19.786 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory -- Creating shared instance of singleton bean 'org.springframework.context.annotation.internalAutowiredAnnotationProcessor'
07:33:19.788 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory -- Creating shared instance of singleton bean 'org.springframework.context.annotation.internalCommonAnnotationProcessor'
07:33:19.799 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory -- Creating shared instance of singleton bean 'album'
07:33:19.858 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory -- Creating shared instance of singleton bean 'albumService'
AlbumService no-arg constructor
AlbumService afterPropertiesSet method
Album Name : ConquestOfParadise
07:33:19.891 [SpringContextShutdownHook] DEBUG org.springframework.context.support.ClassPathXmlApplicationContext -- Closing org.springframework.context.support.ClassPathXmlApplicationContext@ea4a92b, started on Fri Jun 02 07:33:19 IST 2023
AlbumService destroy method - Closing Opened Resources
```

### Pointed out for observation
1. If you look at the logs when the `afterPropertiesSet` method is called, you'll notice that we're initializing or setting the values on the album name field.
2. If you look at the logs when the `destroy` method is called, you'll notice that we're destroying resources from Spring IoC Container or Spring Container.

## @PostConstruct and @PreDestroy Annotations

Spring 2.5 introduces these annotations to invoke bean life cycle methods such as `init` and `delete` methods.

1. `@PostConstruct` The PostConstruct annotation is applied to a method that must be performed after dependency injection has been completed in order to do any initialization. This method must be called before the class may be used.
2. `@PreDestroy` The PreDestroy annotation is applied to a method as a callback notice to indicate that the instance is being deleted by the container. The PreDestroy technique is often used to release resources that it has been holding.
