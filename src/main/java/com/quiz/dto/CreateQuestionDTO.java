package com.quiz.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class CreateQuestionDTO {
    @NotBlank(message = "Le contenu est obligatoire")
    @Size(max = 1000, message = "Le contenu ne peut pas dépasser 1000 caractères")
    private String content;

    @NotEmpty(message = "Les options sont obligatoires")
    private List<String> options;

    @NotBlank(message = "La réponse correcte est obligatoire")
    private String correctAnswer;
} 