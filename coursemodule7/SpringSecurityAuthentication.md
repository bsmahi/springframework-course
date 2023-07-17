# Spring Security Authentication & Authorization

Spring Security is a sophisticated Java application security framework. It provides authentication and authorisation procedures to safeguard the resources of your application. In this example, I'll show you how to use Spring Security to establish authentication.

## Step1: Set up a Spring Boot Project

Create a new Spring Boot project or utilize an existing one first. Spring Initializr (https://start.spring.io/) may be used to construct a basic project structure with all required dependencies.

## Step2: Add Spring Security dependencies

Add the following Spring Security dependencies to your project's `pom.xml` file:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

## Step3: Configure Spring Security

To setup Spring Security, create a configuration class. Create a class called `BasicAuthSecurityConfiguration`

```java
import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true)
public class BasicAuthSecurityConfiguration {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth -> auth.requestMatchers("/public").authenticated());
		
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		http.httpBasic(withDefaults());

		http.csrf(csrf -> csrf.disable());

        http.headers(headersConfigurer -> headersConfigurer.frameOptions(frameOptionsConfig -> frameOptionsConfig.sameOrigin()));

		return http.build();
	}

	@Bean
	public UserDetailsService userDetailService() {

		var user = User.withUsername("user")
			.password("{noop}password123")
			.roles("USER")
			.build();

		return new InMemoryUserDetailsManager(user);
	}
}
```

In this configuration, we allow access to the `/public` endpoint without authentication and require authentication for all other requests. We enable basic authentication and configure an in-memory user store with a single user.

## Step 4: Create Controller with two endpoints called BasicAuthenticationController.java
```java

@RestController
public class BasicAuthenticationController {

    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is a public endpoint.";
    }

    @GetMapping("/private")
    public String privateEndpoint() {
        return "This is a private endpoint.";
    }
}
```

## Step 5: Test the application

Start your Spring Boot application and try accessing the endpoints. You should be redirected to a login page when accessing the `/private` endpoint. Use the username "user" and password "password123" to log in and contains an authorization role as `USER`. After successful authentication, you should be able to access the /private endpoint.

That's all! You used Spring Security to establish basic authentication. This example shows how to establish Spring Security, create an in-memory user store, and protect the endpoints of your application.

