package com.reviewservice.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Passenger extends BaseModel {
	private String name;

	@OneToMany(mappedBy = "passenger")
	private List<Booking> bookings = new ArrayList<>();
}