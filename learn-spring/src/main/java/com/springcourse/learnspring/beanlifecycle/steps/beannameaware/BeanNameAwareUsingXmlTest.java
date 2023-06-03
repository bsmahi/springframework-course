package com.springcourse.learnspring.beanlifecycle.steps.beannameaware;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanNameAwareUsingXmlTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");

        BeanNameAwareInterfaceExample beanNameAwareInterface = (BeanNameAwareInterfaceExample) applicationContext.getBean("beanNameAwareInterfaceExample"); // (1)

        System.out.println("BeanClassName : " + beanNameAwareInterface.getClass());

        applicationContext.registerShutdownHook();
    }
}
