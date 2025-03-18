package com.quiz.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateQuizDTO(
    @NotBlank(message = "Le titre est obligatoire")
    @Size(min = 3, max = 100, message = "Le titre doit contenir entre 3 et 100 caractères")
    String title,
    
    @Size(max = 500, message = "La description ne doit pas dépasser 500 caractères")
    String description,
    Long userId
) {} 