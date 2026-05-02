package com.hotel.employee.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class EmployeeRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @Positive(message = "Age must be positive")
    private Integer age;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotBlank(message = "Job title is required")
    private String job;

    @Positive(message = "Salary must be positive")
    private BigDecimal salary;

    @NotBlank(message = "Phone is required")
    private String phone;

    @NotBlank(message = "Aadhar number is required")
    private String aadhar;

    @Email(message = "Email must be valid")
    @NotBlank(message = "Email is required")
    private String email;
}
