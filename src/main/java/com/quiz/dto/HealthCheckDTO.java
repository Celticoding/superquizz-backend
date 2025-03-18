package com.quiz.dto;

public record HealthCheckDTO(
    String status,
    String databaseStatus,
    String timestamp,
    String version
) {} 