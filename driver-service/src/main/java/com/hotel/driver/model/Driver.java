package com.hotel.driver.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "driver")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Driver {

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

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Company is required")
    private String company;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "Vehicle brand is required")
    private String brand;

    @Column(nullable = false, length = 20)
    private String available = "Yes";

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Location is required")
    private String location;
}
