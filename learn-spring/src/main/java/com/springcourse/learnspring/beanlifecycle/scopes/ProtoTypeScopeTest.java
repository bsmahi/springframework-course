package com.springcourse.learnspring.beanlifecycle.scopes;

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
