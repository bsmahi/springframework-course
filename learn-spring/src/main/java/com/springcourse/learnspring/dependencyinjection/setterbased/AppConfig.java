package com.springcourse.learnspring.dependencyinjection.setterbased;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public com.springcourse.learnspring.dependencyinjection.setterbased.UserService userService() {
       UserService service = new UserService();
       // Setter Injection
       service.setEmailService(emailService());

       return service;
    }

    @Bean
    public com.springcourse.learnspring.dependencyinjection.setterbased.EmailService emailService() {
        return new EmailService();
    }
}
