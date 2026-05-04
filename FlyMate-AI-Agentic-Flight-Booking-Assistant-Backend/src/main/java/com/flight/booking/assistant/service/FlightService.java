package com.flight.booking.assistant.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.flight.booking.assistant.entity.Flight;
import com.flight.booking.assistant.repository.FlightRepository;

@Service
public class FlightService {
	
	private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

	
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public List<Flight> searchFlights(String source, String destination) {
        return flightRepository.findBySourceIgnoreCaseAndDestinationIgnoreCase(source, destination);
    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found with id: " + id));
    }

    public Flight addFlight(Flight flight) {
        return flightRepository.save(flight);
    }
}