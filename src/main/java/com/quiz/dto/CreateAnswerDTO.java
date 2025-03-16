package com.quiz.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateAnswerDTO {
    @NotBlank(message = "L'option sélectionnée est obligatoire")
    private String selectedOption;
} 