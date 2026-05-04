package com.flight.booking.assistant.dto;


import com.flight.booking.assistant.entity.Flight;

public class AgentRecommendationResponse {

    private String message;
    private Flight recommendedFlight;
    private String reason;

    public AgentRecommendationResponse() {
    }

    public AgentRecommendationResponse(String message, Flight recommendedFlight, String reason) {
        this.message = message;
        this.recommendedFlight = recommendedFlight;
        this.reason = reason;
    }

    public String getMessage() {
        return message;
    }

    public Flight getRecommendedFlight() {
        return recommendedFlight;
    }

    public String getReason() {
        return reason;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setRecommendedFlight(Flight recommendedFlight) {
        this.recommendedFlight = recommendedFlight;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}