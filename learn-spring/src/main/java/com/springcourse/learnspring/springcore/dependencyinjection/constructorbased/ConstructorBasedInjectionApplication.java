package com.springcourse.learnspring.springcore.dependencyinjection.constructorbased;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConstructorBasedInjectionApplication {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        userService.notifyUser("SteveJobs - ConstructorBased");
    }
}
