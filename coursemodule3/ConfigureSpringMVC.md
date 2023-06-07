# Create Spring MVC Project
Spring MVC autoconfiguration in a Spring Boot application is extremely simple because Spring Boot supports autoconfiguration for Spring MVC. 

To create a Spring MVC application using Spring Boot, follow these steps:

## Step 1: Set up a new Spring Boot project 
Create a new Maven or Gradle project and add the necessary dependencies for Spring Boot and Spring MVC. In this example, I'll use Maven.

Add the following dependencies to your `pom.xml` file:

```xml
<dependencies>
    <!-- Spring Boot Starter -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Thymeleaf Template Engine (optional) -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
</dependencies>

```
## Step 2: Create a Controller
Make a new Java class that will act as a controller for your Spring MVC application. This class is responsible for handling incoming HTTP requests and defining the application's endpoints.

```java
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

```

## Step3: Create a View

Create a new HTML view file in the **/src/main/resources/templates** directory (assuming you're using Thymeleaf as the template engine). For this example, let's create a file named `hello.html`.

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Spring MVC Example</title>
</head>
<body>
<h1>Spring MVC Example</h1>
<p th:text="${message}"></p>
</body>
</html>
```

## Step 4: Run the Application
You may now launch your Spring Boot application i.e., **LearnSpringApplication**. Spring Boot will manage the startup of the embedded Tomcat server as well as the deployment of your application.

```java
package com.springcourse.learnspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LearnSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnSpringApplication.class, args);
	}

}
```
## Step 5: Test the Application
Open your web browser and visit http://localhost:8080 (assuming the application is running on the default port). You should see the `Welcome to Spring MVC!` message displayed on the page.

That's all! You've used Spring Boot to develop a basic Spring MVC application. In the next steps, we'll add more controllers, views, and business logic as needed.
