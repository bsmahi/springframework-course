package com.springcourse.learnspring.beanlifecycle.beannameaware;

import org.springframework.beans.factory.BeanNameAware;

// In this class, we have implemented BeanNameAware interface and accessed the name of the bean using.
public class BeanNameAwareInterfaceExample implements BeanNameAware {

    @Override
    public void setBeanName(String name) {
        System.out.println("setBeanName called");
        System.out.println("Bean name : " + name);
    }
}
