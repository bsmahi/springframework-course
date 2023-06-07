package com.springcourse.learnspring.springcore.dependencyinjection.setterbased;

import org.springframework.beans.factory.annotation.Autowired;

public class SetterUserService {
    private SetterEmailService setterEmailService;

    // Setter method
    @Autowired
    public void setEmailService(SetterEmailService setterEmailService) {
        this.setterEmailService = setterEmailService;
    }

    public void notifyUser(String username) {
        String message = "Hello, " + username + "! You have a new notification.";
        setterEmailService.sendEmail(message);
    }
}
