package com.springcourse.learnspring.springcore.springawareinterfaces;

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
