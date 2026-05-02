package com.hotel.driver.service;

import com.hotel.driver.dto.DriverRequest;
import com.hotel.driver.model.Driver;
import com.hotel.driver.repository.DriverRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository driverRepository;

    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    public Driver getDriverById(Long id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Driver not found with id: " + id));
    }

    public List<Driver> getDriversByBrand(String brand) {
        return driverRepository.findByBrand(brand);
    }

    public List<Driver> getAvailableDrivers() {
        return driverRepository.findByAvailable("Yes");
    }

    public Driver createDriver(DriverRequest request) {
        Driver driver = new Driver();
        mapToDriver(request, driver);
        return driverRepository.save(driver);
    }

    public Driver updateDriver(Long id, DriverRequest request) {
        Driver driver = getDriverById(id);
        mapToDriver(request, driver);
        return driverRepository.save(driver);
    }

    public Driver updateAvailability(Long id, String available) {
        Driver driver = getDriverById(id);
        driver.setAvailable(available);
        return driverRepository.save(driver);
    }

    public void deleteDriver(Long id) {
        if (!driverRepository.existsById(id)) {
            throw new EntityNotFoundException("Driver not found with id: " + id);
        }
        driverRepository.deleteById(id);
    }

    private void mapToDriver(DriverRequest request, Driver driver) {
        driver.setName(request.getName());
        driver.setAge(request.getAge());
        driver.setGender(request.getGender());
        driver.setCompany(request.getCompany());
        driver.setBrand(request.getBrand());
        driver.setAvailable(request.getAvailable() != null ? request.getAvailable() : "Yes");
        driver.setLocation(request.getLocation());
    }
}
