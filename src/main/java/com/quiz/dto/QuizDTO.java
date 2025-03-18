package com.quiz.dto;

import java.time.LocalDateTime;

public record QuizDTO(
    Long id,
    String title,
    String description,
    Long userId,
    String username,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {} 