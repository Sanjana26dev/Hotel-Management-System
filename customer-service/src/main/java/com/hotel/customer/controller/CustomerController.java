package com.hotel.customer.controller;

import com.hotel.customer.dto.CustomerRequest;
import com.hotel.customer.dto.UpdateCheckRequest;
import com.hotel.customer.model.Customer;
import com.hotel.customer.service.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers(
            @RequestParam(required = false) String roomNumber) {
        if (roomNumber != null && !roomNumber.isBlank()) {
            return ResponseEntity.ok(customerService.getCustomersByRoom(roomNumber));
        }
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(customerService.getCustomerById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/id-number/{idNumber}")
    public ResponseEntity<?> getCustomerByIdNumber(@PathVariable String idNumber) {
        try {
            return ResponseEntity.ok(customerService.getCustomerByIdNumber(idNumber));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/check-in")
    public ResponseEntity<?> checkIn(@Valid @RequestBody CustomerRequest request) {
        Customer customer = customerService.checkIn(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    @PatchMapping("/{id}/check-details")
    public ResponseEntity<?> updateCheckDetails(
            @PathVariable Long id,
            @RequestBody UpdateCheckRequest request) {
        try {
            return ResponseEntity.ok(customerService.updateCheckDetails(id, request));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }

    @PatchMapping("/{id}/check-out")
    public ResponseEntity<?> checkOut(@PathVariable Long id) {
        try {
            customerService.checkOut(id);
            return ResponseEntity.ok(Map.of("message", "Customer checked out successfully"));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        try {
            customerService.deleteCustomer(id);
            return ResponseEntity.ok(Map.of("message", "Customer deleted successfully"));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }
}
