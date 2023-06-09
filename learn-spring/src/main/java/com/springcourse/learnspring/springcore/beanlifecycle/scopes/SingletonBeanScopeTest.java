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
