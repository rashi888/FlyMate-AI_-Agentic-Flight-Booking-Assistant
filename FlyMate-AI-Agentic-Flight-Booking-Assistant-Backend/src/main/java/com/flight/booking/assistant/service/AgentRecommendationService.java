package com.flight.booking.assistant.service;


import com.flight.booking.assistant.dto.AgentRecommendationRequest;
import com.flight.booking.assistant.dto.AgentRecommendationResponse;
import com.flight.booking.assistant.entity.Flight;
import com.flight.booking.assistant.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgentRecommendationService {

    private final FlightRepository flightRepository;

    public AgentRecommendationService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public AgentRecommendationResponse recommendFlight(AgentRecommendationRequest request) {

        List<Flight> flights = flightRepository
                .findBySourceIgnoreCaseAndDestinationIgnoreCase(
                        request.getSource(),
                        request.getDestination()
                );

        if (flights.isEmpty()) {
            return new AgentRecommendationResponse(
                    "No flights found",
                    null,
                    "I could not find any flights for the selected route."
            );
        }

        List<Flight> availableFlights = flights.stream()
                .filter(flight -> flight.getAvailableSeats() != null && flight.getAvailableSeats() > 0)
                .collect(Collectors.toList());

        if (availableFlights.isEmpty()) {
            return new AgentRecommendationResponse(
                    "No seats available",
                    null,
                    "Flights are available for this route, but no seats are currently available."
            );
        }

        List<Flight> budgetFriendlyFlights = availableFlights;

        if (request.getBudget() != null && request.getBudget() > 0) {
            budgetFriendlyFlights = availableFlights.stream()
                    .filter(flight -> flight.getPrice() <= request.getBudget())
                    .collect(Collectors.toList());
        }

        if (budgetFriendlyFlights.isEmpty()) {
            Flight cheapestFlight = availableFlights.stream()
                    .min(Comparator.comparingDouble(Flight::getPrice))
                    .orElse(null);

            return new AgentRecommendationResponse(
                    "No flight found within budget",
                    cheapestFlight,
                    "I could not find a flight within your budget. However, this is the cheapest available option."
            );
        }

        String preference = request.getPreferenceType();

        if (preference == null || preference.isBlank()) {
            preference = "BALANCED";
        }

        Flight recommendedFlight;

        switch (preference.toUpperCase()) {

            case "CHEAPEST":
                recommendedFlight = budgetFriendlyFlights.stream()
                        .min(Comparator.comparingDouble(Flight::getPrice))
                        .orElse(null);
                break;

            case "FASTEST":
                recommendedFlight = budgetFriendlyFlights.stream()
                        .min(Comparator.comparingInt(Flight::getDurationMinutes))
                        .orElse(null);
                break;

            case "BALANCED":
            default:
                recommendedFlight = budgetFriendlyFlights.stream()
                        .min(Comparator.comparingDouble(this::calculateBalancedScore))
                        .orElse(null);
                break;
        }

        String reason = generateReason(recommendedFlight, preference, request.getBudget());

        return new AgentRecommendationResponse(
                "Flight recommendation generated successfully",
                recommendedFlight,
                reason
        );
    }

    private double calculateBalancedScore(Flight flight) {

        double priceScore = flight.getPrice();
        double durationScore = flight.getDurationMinutes() * 10;
        double stopsScore = flight.getStops() * 1000;

        return priceScore + durationScore + stopsScore;
    }

    private String generateReason(Flight flight, String preference, Double budget) {

        if (flight == null) {
            return "No suitable flight could be recommended.";
        }

        String budgetText = "";

        if (budget != null && budget > 0) {
            budgetText = " and it fits within your budget of " + budget;
        }

        switch (preference.toUpperCase()) {

            case "CHEAPEST":
                return "I recommend " + flight.getAirline() + " flight " + flight.getFlightNumber()
                        + " because it is the cheapest available flight" + budgetText + ".";

            case "FASTEST":
                return "I recommend " + flight.getAirline() + " flight " + flight.getFlightNumber()
                        + " because it has the shortest travel duration of "
                        + flight.getDurationMinutes() + " minutes" + budgetText + ".";

            case "BALANCED":
            default:
                return "I recommend " + flight.getAirline() + " flight " + flight.getFlightNumber()
                        + " because it gives a good balance of price, travel duration, and number of stops"
                        + budgetText + ".";
        }
    }
}