package com.drivers.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drivers.application.model.Booking;

public interface BookingRepo extends JpaRepository<Booking, Integer> {

}
