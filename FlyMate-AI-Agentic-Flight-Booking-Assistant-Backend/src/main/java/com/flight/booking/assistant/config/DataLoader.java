package com.flight.booking.assistant.config;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.flight.booking.assistant.entity.Flight;
import com.flight.booking.assistant.repository.FlightRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final FlightRepository flightRepository;

    public DataLoader(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public void run(String... args) {

        if (flightRepository.count() == 0) {

            Flight flight1 = new Flight();
            flight1.setAirline("IndiGo");
            flight1.setFlightNumber("6E-204");
            flight1.setSource("Bangalore");
            flight1.setDestination("Delhi");
            flight1.setDepartureTime(LocalDateTime.of(2026, 6, 10, 8, 30));
            flight1.setArrivalTime(LocalDateTime.of(2026, 6, 10, 11, 15));
            flight1.setDurationMinutes(165);
            flight1.setPrice(5800.0);
            flight1.setAvailableSeats(20);
            flight1.setStops(0);

            Flight flight2 = new Flight();
            flight2.setAirline("Air India");
            flight2.setFlightNumber("AI-502");
            flight2.setSource("Bangalore");
            flight2.setDestination("Delhi");
            flight2.setDepartureTime(LocalDateTime.of(2026, 6, 10, 10, 0));
            flight2.setArrivalTime(LocalDateTime.of(2026, 6, 10, 13, 5));
            flight2.setDurationMinutes(185);
            flight2.setPrice(6500.0);
            flight2.setAvailableSeats(15);
            flight2.setStops(0);

            flightRepository.save(flight1);
            flightRepository.save(flight2);

            System.out.println("Sample flight data inserted successfully.");
        }
    }
}