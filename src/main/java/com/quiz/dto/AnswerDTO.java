package com.quiz.dto;

import lombok.Data;

@Data
public class AnswerDTO {
    private Long id;
    private Long questionId;
    private Long userId;
    private String selectedOption;
    private boolean isCorrect;
} 