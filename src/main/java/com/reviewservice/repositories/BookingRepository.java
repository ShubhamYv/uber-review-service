package com.reviewservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reviewservice.models.Booking;
import com.reviewservice.models.Driver;

public interface BookingRepository extends JpaRepository<Booking, Long> {
	List<Booking> findAllByDriverId(Long driverId);

	List<Booking> findAllByDriverIn(List<Driver> drivers);
}