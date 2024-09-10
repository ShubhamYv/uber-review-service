package com.reviewservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reviewservice.models.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
	List<Booking> findAllByDriverId(Long driverId);
}