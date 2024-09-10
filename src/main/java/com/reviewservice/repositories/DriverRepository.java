package com.reviewservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.reviewservice.models.Driver;

public interface DriverRepository extends JpaRepository<Driver, Long> {

	Optional<Driver> findByIdAndLicenseNumber(Long id, String licenseNumber);

	// RAW mysql query , error is thrown at runtime is query has issue
	@Query(nativeQuery = true, value = "SELECT * FROM Driver WHERE id = :id AND license_number = :license")
	Optional<Driver> rawFindByIdAndLicenseNumber(Long id, String license);

	// Hibernate query, error is thrown at compile time
	@Query("From Driver as d where d.id = :id AND d.licenseNumber = :ln")
	Optional<Driver> hqlFindByIdAndLicense(Long id, String ln);

}