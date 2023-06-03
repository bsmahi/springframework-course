package com.springcourse.learnspring.beanlifecycle.steps;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class EmployeeService {
    private Employee employee;

    public EmployeeService() {
        System.out.println("EmployeeService no-arg constructor");
    }

    @PostConstruct
    public void Initialize() {
        System.out.println("Initializing the bean with PostConstruct");
    }

    @PreDestroy
    public void cleanUp() {
        System.out.println("Cleaning up the resources with Pre Destroy");
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
