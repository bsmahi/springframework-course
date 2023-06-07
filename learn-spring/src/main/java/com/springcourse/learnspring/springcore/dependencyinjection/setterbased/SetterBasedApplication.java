package com.springcourse.learnspring.springcore.dependencyinjection.setterbased;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SetterBasedApplication {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SetterBasedAppConfig.class);

        SetterUserService setterUserService = context.getBean(SetterUserService.class);
        setterUserService.notifyUser("Steve Jobs - SetterBased");
    }
}
