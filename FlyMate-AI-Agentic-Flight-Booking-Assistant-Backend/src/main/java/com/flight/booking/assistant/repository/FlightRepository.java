package com.flight.booking.assistant.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flight.booking.assistant.entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findBySourceIgnoreCaseAndDestinationIgnoreCase(String source, String destination);
}