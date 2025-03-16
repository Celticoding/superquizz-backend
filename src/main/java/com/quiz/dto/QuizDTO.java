package com.quiz.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class QuizDTO {
    private Long id;
    private String title;
    private String description;
    private Long createdById;
    private String createdByUsername;
    private List<QuestionDTO> questions = new ArrayList<>();
} 