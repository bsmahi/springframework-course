package com.springcourse.learnspring.springcore.dependencyinjection.setterbased;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SetterBasedAppConfig {

    @Bean
    public SetterUserService setterUserService() {
       SetterUserService service = new SetterUserService();
       // Setter Injection
       service.setEmailService(setterEmailService());

       return service;
    }

    @Bean
    public SetterEmailService setterEmailService() {
        return new SetterEmailService();
    }
}
