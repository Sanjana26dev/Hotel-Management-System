package com.hotel.customer.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateCheckRequest {

    private String roomNumber;
    private String name;
    private String status;
    private BigDecimal deposit;
}
