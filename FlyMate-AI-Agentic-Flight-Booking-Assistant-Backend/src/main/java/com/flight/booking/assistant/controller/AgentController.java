package com.flight.booking.assistant.controller;

import com.flight.booking.assistant.dto.AgentRecommendationRequest;
import com.flight.booking.assistant.dto.AgentRecommendationResponse;
import com.flight.booking.assistant.service.AgentRecommendationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/agent")
@CrossOrigin(origins = "http://localhost:5173")
public class AgentController {

    private final AgentRecommendationService agentRecommendationService;

    public AgentController(AgentRecommendationService agentRecommendationService) {
        this.agentRecommendationService = agentRecommendationService;
    }

    @PostMapping("/recommend")
    public AgentRecommendationResponse recommendFlight(@RequestBody AgentRecommendationRequest request) {
        return agentRecommendationService.recommendFlight(request);
    }
}