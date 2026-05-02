package com.hotel.customer.service;

import com.hotel.customer.dto.CustomerRequest;
import com.hotel.customer.dto.UpdateCheckRequest;
import com.hotel.customer.model.Customer;
import com.hotel.customer.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));
    }

    public Customer getCustomerByIdNumber(String idNumber) {
        return customerRepository.findByIdNumber(idNumber)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with ID number: " + idNumber));
    }

    public List<Customer> getCustomersByRoom(String roomNumber) {
        return customerRepository.findByRoomNumber(roomNumber);
    }

    public Customer checkIn(CustomerRequest request) {
        Customer customer = new Customer();
        mapToCustomer(request, customer);
        customer.setStatus("Checked-In");
        return customerRepository.save(customer);
    }

    public Customer updateCheckDetails(Long id, UpdateCheckRequest request) {
        Customer customer = getCustomerById(id);
        if (request.getRoomNumber() != null) {
            customer.setRoomNumber(request.getRoomNumber());
        }
        if (request.getName() != null) {
            customer.setName(request.getName());
        }
        if (request.getStatus() != null) {
            customer.setStatus(request.getStatus());
        }
        if (request.getDeposit() != null) {
            customer.setDeposit(request.getDeposit());
        }
        return customerRepository.save(customer);
    }

    public void checkOut(Long id) {
        Customer customer = getCustomerById(id);
        customer.setStatus("Checked-Out");
        customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new EntityNotFoundException("Customer not found with id: " + id);
        }
        customerRepository.deleteById(id);
    }

    private void mapToCustomer(CustomerRequest request, Customer customer) {
        customer.setIdType(request.getIdType());
        customer.setIdNumber(request.getIdNumber());
        customer.setName(request.getName());
        customer.setGender(request.getGender());
        customer.setCountry(request.getCountry());
        customer.setRoomNumber(request.getRoomNumber());
        customer.setStatus(request.getStatus() != null ? request.getStatus() : "Checked-In");
        customer.setDeposit(request.getDeposit());
    }
}
