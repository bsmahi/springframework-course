package com.springcourse.learnspring.springcore.springawareinterfaces;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

public class BeanFactoryAwareImpl implements BeanFactoryAware {

    private BeanFactory beanFactory;

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("setBeanFactory method called");
        this.beanFactory = beanFactory;
    }

    public void getStudentDetails() {
        Student student = (Student) beanFactory.getBean("student");
        System.out.println("StudentName.." + student.getName());

        System.out.println(beanFactory.isSingleton("student"));
    }
}
