package com.springcourse.learnspring.springcore.beanlifecycle.steps.beannameaware;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanNameAwareConfig {

    @Bean(name = "myBeanNameAware")
    // @Bean
    public BeanNameAwareInterfaceExample getBeanNameAwareInterfaceExample() {
        return new BeanNameAwareInterfaceExample();
    }
}
