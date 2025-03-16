package com.quiz.mapper;

import com.quiz.dto.QuizDTO;
import com.quiz.model.Quiz;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuizMapper {
    
    private final QuestionMapper questionMapper;

    public QuizDTO toDTO(Quiz quiz) {
        if (quiz == null) {
            return null;
        }

        QuizDTO dto = new QuizDTO();
        dto.setId(quiz.getId());
        dto.setTitle(quiz.getTitle());
        dto.setDescription(quiz.getDescription());
        dto.setCreatedById(quiz.getCreatedBy().getId());
        dto.setCreatedByUsername(quiz.getCreatedBy().getUsername());
        if (quiz.getQuestions() != null) {
            dto.setQuestions(quiz.getQuestions().stream()
                .map(questionMapper::toDTO)
                .toList());
        }
        return dto;
    }

    public Quiz toEntity(QuizDTO dto) {
        if (dto == null) {
            return null;
        }

        Quiz quiz = new Quiz();
        quiz.setId(dto.getId());
        quiz.setTitle(dto.getTitle());
        quiz.setDescription(dto.getDescription());
        return quiz;
    }
} 