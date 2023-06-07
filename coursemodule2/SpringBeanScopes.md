# Spring Bean Scopes

The **scope** of a bean defines what type of object the Spring IoC container must build for the bean defined.

While defining the bean in the spring configuration file(for example, **beans.xml**), we may specify the **scope** of the bean.

Spring offers 5 scopes for a bean by default; however, we may customize and build custom scopes.

### Spring supports the following types of bean scopes out of the box.

1. **Singleton**: Per Spring container, return a single bean object. By default, all the beans defined in spring will have **singleton** scope
2. **Prototype**: When the Spring container is requested, return a new bean instance each time.
3. **Request**: Returns a single bean object per **HTTP request** and is only valid in the context of a web-aware Spring ApplicationContext, which implies only in the web application.
4. **Session**: Returns a single bean object per **HTTP session** and is only valid in the context of a web-aware Spring ApplicationContext, which implies only in the web application.
5. **GlobalSession**: Return a single bean instance per global HTTP session. In portlet applications, session scope is considered as global session scope. Hence, only valid when used in a portlet context.

Now consider the Singleton bean as an example.

## Step 1: Create a class called `Employee` as below under the package **com.springcourse.learnspring.springcore.beanlifecycle.scopes**

```java
package com.springcourse.learnspring.springcore.beanlifecycle.scopes;

public class Employee {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```
## Step 2: Define the Employee bean in the spring configuration file under **/src/main/resources**
Spring Configuration File Name is: **beans-scope.xml**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
       http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context-3.2.xsd">

    <context:annotation-config/>

    <bean id="employee" class="com.springcourse.learnspring.springcore.beanlifecycle.scopes.Employee">
        <property name="name" value="Steve"/>
    </bean>
</beans>
```
### Note
In The preceding spring configuration file, we have not declared any scope for the bean employee, _despite the fact that the scope of a bean is Singleton since it is the default scope of a bean.

## Step 3: Let's execute file to check the bean's scope by creating `SingletonBeanScopeTest`

```java
package com.springcourse.learnspring.springcore.beanlifecycle.scopes;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SingletonBeanScopeTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans-scope.xml");

        // Load an Employee object
        Employee employeeOne = (Employee) applicationContext.getBean("employee");

        System.out.println("Name : " + employeeOne.getName());

        Employee employeeTwo = (Employee) applicationContext.getBean("employee");

        System.out.println("Name : " + employeeTwo.getName());

        System.out.println(employeeOne == employeeTwo);

        applicationContext.registerShutdownHook();

    }
}
```
```log
18:54:19.683 [main] DEBUG org.springframework.context.support.ClassPathXmlApplicationContext -- Refreshing org.springframework.context.support.ClassPathXmlApplicationContext@ea4a92b
18:54:19.956 [main] DEBUG org.springframework.beans.factory.xml.XmlBeanDefinitionReader -- Loaded 7 bean definitions from class path resource [beans-scope.xml]
18:54:19.983 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory -- Creating shared instance of singleton bean 'org.springframework.context.annotation.internalConfigurationAnnotationProcessor'
18:54:20.027 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory -- Creating shared instance of singleton bean 'org.springframework.context.event.internalEventListenerProcessor'
18:54:20.029 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory -- Creating shared instance of singleton bean 'org.springframework.context.event.internalEventListenerFactory'
18:54:20.031 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory -- Creating shared instance of singleton bean 'org.springframework.context.annotation.internalAutowiredAnnotationProcessor'
18:54:20.033 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory -- Creating shared instance of singleton bean 'org.springframework.context.annotation.internalCommonAnnotationProcessor'
18:54:20.046 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory -- Creating shared instance of singleton bean 'employee'
Name : Steve
Name : Steve
true
18:54:20.134 [SpringContextShutdownHook] DEBUG org.springframework.context.support.ClassPathXmlApplicationContext -- Closing org.springframework.context.support.ClassPathXmlApplicationContext@ea4a92b, started on Mon Jun 05 18:54:19 IST 2023
```
## Observations on output
* Despite the fact that we used the getBean() function twice to obtain the bean. We can observe that the two occurrences are identical due to the default scope, i.e., **Singleton**.

* When we compare two instances using ==, it always verifies the memory address of the two objects and returns `true`, indicating that they are the same.

## Step 4: Now let’s modify spring configuration file(**beans-scope.xml**) to check **Prototype** scope of a bean 

Let's create another bean id `employeeprototype` by defining **scope="prototype"**

```xml
<bean id="employeeprototype" class="com.springcourse.learnspring.springcore.beanlifecycle.scopes.Employee" scope="prototype">
        <property name="name" value="David"/>
</bean>
```

## Step 5: Let's execute file to check the bean's scope by creating `ProtoTypeScopeTest`

```java
package com.springcourse.learnspring.springcore.beanlifecycle.scopes;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProtoTypeScopeTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans-scope.xml");

        // Load an Employee object for the scope - Prototype
        Employee employeeOne = (Employee) applicationContext.getBean("employeeprototype");

        employeeOne.setName("employeeOne");

        System.out.println("Name : " + employeeOne.getName());

        Employee employeeTwo = (Employee) applicationContext.getBean("employeeprototype");

        System.out.println("Name : " + employeeTwo.getName());

        System.out.println(employeeOne == employeeTwo);

        applicationContext.registerShutdownHook();

    }
}
```

```log
19:03:53.546 [main] DEBUG org.springframework.context.support.ClassPathXmlApplicationContext -- Refreshing org.springframework.context.support.ClassPathXmlApplicationContext@ea4a92b
19:03:53.840 [main] DEBUG org.springframework.beans.factory.xml.XmlBeanDefinitionReader -- Loaded 7 bean definitions from class path resource [beans-scope.xml]
19:03:53.869 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory -- Creating shared instance of singleton bean 'org.springframework.context.annotation.internalConfigurationAnnotationProcessor'
19:03:53.919 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory -- Creating shared instance of singleton bean 'org.springframework.context.event.internalEventListenerProcessor'
19:03:53.921 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory -- Creating shared instance of singleton bean 'org.springframework.context.event.internalEventListenerFactory'
19:03:53.923 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory -- Creating shared instance of singleton bean 'org.springframework.context.annotation.internalAutowiredAnnotationProcessor'
19:03:53.924 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory -- Creating shared instance of singleton bean 'org.springframework.context.annotation.internalCommonAnnotationProcessor'
19:03:53.934 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory -- Creating shared instance of singleton bean 'employee'
Name : employeeOne
Name : David
false
19:03:54.028 [SpringContextShutdownHook] DEBUG org.springframework.context.support.ClassPathXmlApplicationContext -- Closing org.springframework.context.support.ClassPathXmlApplicationContext@ea4a92b, started on Mon Jun 05 19:03:53 IST 2023
```
## Observations on output
* We obtained the bean **employeeOne** by invoking the getBean() function.

* We changed its name to `David`, and then we retrieved the bean **employeeTwo** by executing the getBean() function, which printed its name.

* Because the scope of the Employee bean is set to **prototype**, employeeTwo has no effect on the new Name.

* Also, we used == to compare employeeOne and employeeTwo, and it returned false, indicating that they are distinct objects.

> As a result, prototype scope assures that each getBean() call returns the new object.