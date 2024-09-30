package com.reviewservice.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entityservice.models.Driver;


public interface DriverRepository extends JpaRepository<Driver, Long> {

	Optional<Driver> findByIdAndLicenseNumber(Long id, String licenseNumber);

	List<Driver> findAllByIdIn(List<Long> driverIds);

}