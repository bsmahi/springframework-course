package com.springcourse.learnspring.springawareinterfaces;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanFactoryAwareTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beanfactoryaware-bean.xml");

        BeanFactoryAwareImpl beanFactoryAwareImpl = (BeanFactoryAwareImpl) applicationContext.getBean("beanFactoryAware");

        System.out.println("Bean factory aware output");
        beanFactoryAwareImpl.getStudentDetails();
        applicationContext.registerShutdownHook();
    }
}
