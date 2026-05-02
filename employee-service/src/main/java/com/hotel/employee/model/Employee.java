package com.hotel.employee.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Name is required")
    private String name;

    @Column(nullable = false)
    @Positive(message = "Age must be positive")
    private Integer age;

    @Column(nullable = false, length = 10)
    @NotBlank(message = "Gender is required")
    private String gender;

    @Column(nullable = false, length = 60)
    @NotBlank(message = "Job title is required")
    private String job;

    @Column(nullable = false, precision = 10, scale = 2)
    @Positive(message = "Salary must be positive")
    private BigDecimal salary;

    @Column(nullable = false, length = 15)
    @NotBlank(message = "Phone is required")
    private String phone;

    @Column(nullable = false, length = 20)
    @NotBlank(message = "Aadhar number is required")
    private String aadhar;

    @Column(nullable = false, length = 100)
    @Email(message = "Email must be valid")
    @NotBlank(message = "Email is required")
    private String email;
}
