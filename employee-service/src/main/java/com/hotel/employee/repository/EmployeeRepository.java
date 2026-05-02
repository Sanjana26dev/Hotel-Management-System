package com.hotel.employee.repository;

import com.hotel.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByJob(String job);

    List<Employee> findByJobContainingIgnoreCase(String keyword);
}
