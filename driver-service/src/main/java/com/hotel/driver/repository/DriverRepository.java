package com.hotel.driver.repository;

import com.hotel.driver.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DriverRepository extends JpaRepository<Driver, Long> {

    List<Driver> findByBrand(String brand);

    List<Driver> findByAvailable(String available);

    List<Driver> findByLocation(String location);
}
