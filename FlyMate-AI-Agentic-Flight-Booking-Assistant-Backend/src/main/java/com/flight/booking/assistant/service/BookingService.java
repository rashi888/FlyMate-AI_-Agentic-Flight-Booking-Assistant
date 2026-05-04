package com.flight.booking.assistant.service;

import com.flight.booking.assistant.dto.BookingRequest;
import com.flight.booking.assistant.entity.Booking;
import com.flight.booking.assistant.entity.Flight;
import com.flight.booking.assistant.repository.BookingRepository;
import com.flight.booking.assistant.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;

    public BookingService(BookingRepository bookingRepository, FlightRepository flightRepository) {
        this.bookingRepository = bookingRepository;
        this.flightRepository = flightRepository;
    }

    public Booking createBooking(BookingRequest request) {

        Flight flight = flightRepository.findById(request.getFlightId())
                .orElseThrow(() -> new RuntimeException("Flight not found with id: " + request.getFlightId()));

        if (flight.getAvailableSeats() == null || flight.getAvailableSeats() <= 0) {
            throw new RuntimeException("No seats available for this flight");
        }

        Booking booking = new Booking();
        booking.setPassengerName(request.getPassengerName());
        booking.setPassengerEmail(request.getPassengerEmail());
        booking.setPassengerPhone(request.getPassengerPhone());
        booking.setStatus("CONFIRMED");
        booking.setBookingDate(LocalDateTime.now());
        booking.setTotalAmount(flight.getPrice());
        booking.setFlight(flight);

        flight.setAvailableSeats(flight.getAvailableSeats() - 1);
        flightRepository.save(flight);

        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));
    }

    public Booking cancelBooking(Long id) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));

        if ("CANCELLED".equalsIgnoreCase(booking.getStatus())) {
            throw new RuntimeException("Booking is already cancelled");
        }

        booking.setStatus("CANCELLED");

        Flight flight = booking.getFlight();
        flight.setAvailableSeats(flight.getAvailableSeats() + 1);
        flightRepository.save(flight);

        return bookingRepository.save(booking);
    }
}