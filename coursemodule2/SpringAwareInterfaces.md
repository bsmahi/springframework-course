# Spring Aware Interfaces for Beans

**Spring Aware** interfaces in Spring Framework are a means to integrate Spring functionality into your application components. These APIs enable your beans to become aware of the Spring container and associated services, such as **accessing the application context** and **receiving notifications about bean lifecycle events**. Your beans can communicate with the Spring framework and take use of its advantages by implementing these interfaces.

Spring offers a plethora of `Aware` interfaces to make the beans aware of this.

All we need to do is make our bean implement the `Aware` interface and its `setter` function.

`org.springframework.beans.factory.Aware` is the root [marker interface](https://en.wikipedia.org/wiki/Marker_interface_pattern).

All the `Aware` interfaces that we employ are sub-interfaces of the `Aware` interface.

Some of the commonly used `Aware` Interfaces are

1. **ApplicationContextAware**: A bean that implements this interface may obtain the **current application context** and use it to invoke any service from the application context.
2. **BeanFactoryAware**: Beans implementing this interface can obtain the **current bean factory** and use it to contact any service provided by the bean factory.
3. **BeanNameAware**: The **name** of a bean that implements this interface can be specified in the Spring container.
4. **MessageSourceAware**: Beans that implement this interface have access to the **message source** object, which is used for **internationalisation**.
5. **ServletContextAware**: Bean implementing this interface can get access to **ServletContext**, which is used to access servlet context parameters and attributes.
6. **ServletConfigAware**: Beans that implement this interface have access to the **ServletConfig** object, which is used to get servlet configuration settings.
7. **ApplicationEventPublisherAware**: Beans that implement this interface can publish **application events**, and we must establish listeners to listen to these events.
8. **ResourceLoaderAware**: Bean implementing this interface can load the resources from the classpath or any external file.

In the previous sections, we saw how to use the BeanNameAware interface to retrieve the bean name from the spring container.

Let's understand **ApplicationContextAware** and **BeanFactoryAware** with an example

## Step 1: First Create Student POJO class.

```java
package com.springcourse.learnspring.springawareinterfaces;

public class Student {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
```

## Step 2: Create the following class that implements ApplicationContextAware

```java
package com.springcourse.learnspring.springawareinterfaces;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextAwareImpl implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("setApplicationContext method called");
        this.applicationContext = applicationContext;
    }

    public void getStudentDetails() {
        Student student = (Student) applicationContext.getBean("student");
        System.out.println("StudentName.." + student.getName());

        System.out.println(applicationContext.isSingleton("student"));
    }
}
```
In this class, we are attempting to access **ApplicationContext** by implementing **ApplicationContextAware**.

So, using **ApplicationContext**, we obtained the **Student** bean from the context and also used **ApplicationContext** to determine whether the **Student** bean is a singleton or not.

## Step 3: Now create the applicationcontextaware-bean.xml file by defining the beans

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans-3.2.xsd">
    
    <bean id="applicationContextAware"
          class="com.springcourse.learnspring.springawareinterfaces.ApplicationContextAwareImpl"/>

    <bean id="student" class="com.springcourse.learnspring.springawareinterfaces.Student">
        <property name="name" value="Steve"/>
    </bean>

</beans>
```
## Step 4: Create an ApplicationContextAwareTest class to validate the output.

```java
package com.springcourse.learnspring.springawareinterfaces;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextAwareTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationcontextaware-bean.xml");

        ApplicationContextAwareImpl applicationContextAwareImpl = (ApplicationContextAwareImpl) applicationContext.getBean("applicationContextAware");

        System.out.println("Application context aware output");
        applicationContextAwareImpl.getStudentDetails();

        applicationContext.registerShutdownHook();
    }
}
```

Run the preceding class to view the output shown below.

```log
08:57:16.374 [main] DEBUG org.springframework.context.support.ClassPathXmlApplicationContext -- Refreshing org.springframework.context.support.ClassPathXmlApplicationContext@ea4a92b
08:57:16.630 [main] DEBUG org.springframework.beans.factory.xml.XmlBeanDefinitionReader -- Loaded 2 bean definitions from class path resource [applicationcontextaware-bean.xml]
08:57:16.695 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory -- Creating shared instance of singleton bean 'applicationContextAware'
setApplicationContext method called
08:57:16.710 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory -- Creating shared instance of singleton bean 'student'
Application context aware output
StudentName..Steve
true
08:57:16.797 [SpringContextShutdownHook] DEBUG org.springframework.context.support.ClassPathXmlApplicationContext -- Closing org.springframework.context.support.ClassPathXmlApplicationContext@ea4a92b, started on Sat Jun 03 08:57:16 IST 2023
```
As we can see from the result. 
1. The container sets the **ApplicationContext** first, as we have built **ApplicationContextAware**.
2. The **bean value is then retrieved** from the application context, i.e., getting student details.
3. The **scope** of the bean is then checked using application context. 