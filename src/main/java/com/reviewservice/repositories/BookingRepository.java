package com.reviewservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reviewservice.models.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}