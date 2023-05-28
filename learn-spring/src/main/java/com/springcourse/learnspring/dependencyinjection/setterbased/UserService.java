package com.springcourse.learnspring.dependencyinjection.setterbased;

public class UserService {
    private EmailService emailService;

    // Setter method
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void notifyUser(String username) {
        String message = "Hello, " + username + "! You have a new notification.";
        emailService.sendEmail(message);
    }
}
