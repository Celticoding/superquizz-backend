package com.quiz.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateAnswerDTO(
    @NotBlank(message = "La réponse sélectionnée est obligatoire")
    @Size(max = 200, message = "La réponse ne doit pas dépasser 200 caractères")
    String selectedOption,
    Long userId
) {} 