package com.springcourse.learnspring.beanlifecycle.beannameaware;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanNameAwareUsingConfigTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanNameAwareConfig.class); // (1)

        BeanNameAwareInterfaceExample contextBean = context.getBean(BeanNameAwareInterfaceExample.class); // (2)

        context.registerShutdownHook(); // (3)
    }
}
