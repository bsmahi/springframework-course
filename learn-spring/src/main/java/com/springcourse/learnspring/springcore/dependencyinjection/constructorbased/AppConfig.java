package com.springcourse.learnspring.springcore.dependencyinjection.constructorbased;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public UserService userService() {
        return new UserService(emailService());
    }

    @Bean
    public EmailService emailService() {
        return new EmailService();
    }
}
