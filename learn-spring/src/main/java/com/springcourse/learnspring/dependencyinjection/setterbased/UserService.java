package com.springcourse.learnspring.dependencyinjection.setterbased;

import org.springframework.beans.factory.annotation.Autowired;

public class UserService {
    private EmailService emailService;

    // Setter method
    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void notifyUser(String username) {
        String message = "Hello, " + username + "! You have a new notification.";
        emailService.sendEmail(message);
    }
}
