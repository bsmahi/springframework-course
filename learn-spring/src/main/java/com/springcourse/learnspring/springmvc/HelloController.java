package com.springcourse.learnspring.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/")
    public String helloWorld(Model model) {
        model.addAttribute("message", "Welcome to Spring MVC!");
        return "hello";
    }
}
