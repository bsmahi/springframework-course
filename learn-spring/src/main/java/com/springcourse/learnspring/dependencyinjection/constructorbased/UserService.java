package com.springcourse.learnspring.dependencyinjection.constructorbased;

import org.springframework.beans.factory.annotation.Autowired;

public class UserService {
    private final EmailService emailService;

    // Constructor Injection
    @Autowired
    public UserService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void notifyUser(String username) {
        String message = "Hello, " + username + "! You have a new notification.";
        emailService.sendEmail(message);
    }
}
