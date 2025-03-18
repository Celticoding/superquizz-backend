package com.quiz.controller;

import com.quiz.dto.HealthCheckDTO;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/health")
@RequiredArgsConstructor
public class HealthCheckController {

    private final EntityManager entityManager;

    @Value("${spring.application.version:unknown}")
    private String applicationVersion;

    @GetMapping
    public ResponseEntity<HealthCheckDTO> checkHealth() {
        String databaseStatus = checkDatabaseConnection();
        
        HealthCheckDTO healthCheck = new HealthCheckDTO(
            "UP",
            databaseStatus,
            LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
            applicationVersion
        );

        return ResponseEntity.ok(healthCheck);
    }

    private String checkDatabaseConnection() {
        try {
            entityManager.createNativeQuery("SELECT 1").getSingleResult();
            return "UP";
        } catch (Exception e) {
            return "DOWN";
        }
    }
} 