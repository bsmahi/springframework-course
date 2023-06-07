package com.springcourse.learnspring.springcore.beanlifecycle.steps;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PostConstructPreDestroyTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("annotation-beans.xml");

        // Load EmployeeService object
        EmployeeService employeeService = (EmployeeService) applicationContext.getBean("employeeService");
        System.out.println("Employee Name : " + employeeService.getEmployee().getEmployeeName());

        applicationContext.registerShutdownHook();

    }
}
