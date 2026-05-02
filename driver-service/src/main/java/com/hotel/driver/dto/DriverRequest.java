package com.hotel.driver.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class DriverRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @Positive(message = "Age must be positive")
    private Integer age;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotBlank(message = "Company is required")
    private String company;

    @NotBlank(message = "Vehicle brand is required")
    private String brand;

    private String available = "Yes";

    @NotBlank(message = "Location is required")
    private String location;
}
