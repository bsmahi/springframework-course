package com.springcourse.learnspring.beanlifecycle.beannameaware;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanNameAwareUsingXmlTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");

        BeanNameAwareInterfaceExample beanNameAwareInterfaceImpl = (BeanNameAwareInterfaceExample) applicationContext.getBean("beanNameAwareInterfaceExample"); // (1)

        applicationContext.registerShutdownHook();
    }
}
