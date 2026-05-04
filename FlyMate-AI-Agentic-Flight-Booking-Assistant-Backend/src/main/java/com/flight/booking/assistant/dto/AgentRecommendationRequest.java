package com.flight.booking.assistant.dto;


public class AgentRecommendationRequest {

    private String source;
    private String destination;
    private Double budget;
    private String preferenceType;

    public AgentRecommendationRequest() {
    }

    public AgentRecommendationRequest(String source, String destination, Double budget, String preferenceType) {
        this.source = source;
        this.destination = destination;
        this.budget = budget;
        this.preferenceType = preferenceType;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public String getPreferenceType() {
        return preferenceType;
    }

    public void setPreferenceType(String preferenceType) {
        this.preferenceType = preferenceType;
    }
}