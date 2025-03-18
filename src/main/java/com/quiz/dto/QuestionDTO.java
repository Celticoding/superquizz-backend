package com.quiz.dto;

import java.time.LocalDateTime;

public record QuestionDTO(
    Long id,
    String questionContent,
    String correctAnswer,
    Long quizId,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {} 