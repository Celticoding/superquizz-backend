package com.quiz.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class QuestionDTO {
    private Long id;
    private Long quizId;
    private String content;
    private List<String> options = new ArrayList<>();
    // Note: correctAnswer n'est pas inclus dans le DTO pour ne pas exposer la réponse
    private List<AnswerDTO> answers = new ArrayList<>();
} 