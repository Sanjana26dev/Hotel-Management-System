package com.hotel.room.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "room")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_number", unique = true, nullable = false, length = 10)
    @NotBlank(message = "Room number is required")
    private String roomNumber;

    @Column(nullable = false, length = 20)
    private String availability = "Available";

    @Column(name = "clean_status", nullable = false, length = 20)
    private String cleanStatus = "Clean";

    @Column(nullable = false, precision = 10, scale = 2)
    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private BigDecimal price;

    @Column(name = "bed_type", nullable = false, length = 30)
    @NotBlank(message = "Bed type is required")
    private String bedType;
}
