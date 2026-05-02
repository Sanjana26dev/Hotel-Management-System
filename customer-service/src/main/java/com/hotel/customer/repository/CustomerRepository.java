package com.hotel.customer.repository;

import com.hotel.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByIdNumber(String idNumber);

    List<Customer> findByRoomNumber(String roomNumber);

    List<Customer> findByStatus(String status);
}
