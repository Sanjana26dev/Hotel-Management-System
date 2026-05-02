package com.hotel.customer.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_type", nullable = false, length = 30)
    @NotBlank(message = "ID type is required")
    private String idType;

    @Column(name = "id_number", nullable = false, length = 50)
    @NotBlank(message = "ID number is required")
    private String idNumber;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Name is required")
    private String name;

    @Column(nullable = false, length = 10)
    @NotBlank(message = "Gender is required")
    private String gender;

    @Column(nullable = false, length = 60)
    @NotBlank(message = "Country is required")
    private String country;

    @Column(name = "room_number", nullable = false, length = 10)
    @NotBlank(message = "Room number is required")
    private String roomNumber;

    @Column(nullable = false, length = 50)
    private String status = "Checked-In";

    @Column(precision = 10, scale = 2)
    private BigDecimal deposit;
}
