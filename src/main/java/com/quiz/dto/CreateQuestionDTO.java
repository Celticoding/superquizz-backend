package com.quiz.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;

public record CreateQuestionDTO(
    @NotBlank(message = "Le texte de la question est obligatoire")
    @Size(min = 3, max = 500, message = "Le texte de la question doit contenir entre 3 et 500 caractères")
    String questionContent,
    
    @NotBlank(message = "La réponse correcte est obligatoire")
    @Size(max = 200, message = "La réponse ne doit pas dépasser 200 caractères")
    String correctAnswer,
    
    List<String> options
) {} 