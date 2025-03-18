package com.quiz.dto;

import java.time.LocalDateTime;

public record AnswerDTO(
    Long id,
    String selectedOption,
    Long questionId,
    Long userId,
    String username,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {} 