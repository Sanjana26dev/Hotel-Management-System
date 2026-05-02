package com.hotel.employee.service;

import com.hotel.employee.dto.EmployeeRequest;
import com.hotel.employee.model.Employee;
import com.hotel.employee.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + id));
    }

    public List<Employee> getEmployeesByJob(String job) {
        return employeeRepository.findByJob(job);
    }

    public List<Employee> searchByJob(String keyword) {
        return employeeRepository.findByJobContainingIgnoreCase(keyword);
    }

    public Employee createEmployee(EmployeeRequest request) {
        Employee employee = new Employee();
        mapToEmployee(request, employee);
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, EmployeeRequest request) {
        Employee employee = getEmployeeById(id);
        mapToEmployee(request, employee);
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EntityNotFoundException("Employee not found with id: " + id);
        }
        employeeRepository.deleteById(id);
    }

    private void mapToEmployee(EmployeeRequest request, Employee employee) {
        employee.setName(request.getName());
        employee.setAge(request.getAge());
        employee.setGender(request.getGender());
        employee.setJob(request.getJob());
        employee.setSalary(request.getSalary());
        employee.setPhone(request.getPhone());
        employee.setAadhar(request.getAadhar());
        employee.setEmail(request.getEmail());
    }
}
