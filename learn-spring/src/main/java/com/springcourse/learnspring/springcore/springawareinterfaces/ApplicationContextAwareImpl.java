package com.springcourse.learnspring.springcore.springawareinterfaces;

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
