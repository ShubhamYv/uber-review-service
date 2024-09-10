package com.reviewservice.models;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking extends BaseModel {

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	private Review review;

	@Enumerated(value = EnumType.STRING)
	private BookingStatus bookingStatus;

	@Temporal(value = TemporalType.TIMESTAMP)
	private Date startTime;

	@Temporal(value = TemporalType.TIMESTAMP)
	private Date endTime;

	private Long totalDistance;

    @ManyToOne
    private Driver driver;

    @ManyToOne
    private Passenger passenger;
}