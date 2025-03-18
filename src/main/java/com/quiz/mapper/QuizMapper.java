package com.quiz.mapper;

import com.quiz.dto.QuizDTO;
import com.quiz.dto.CreateQuizDTO;
import com.quiz.model.Quiz;
import org.springframework.stereotype.Component;

@Component
public class QuizMapper {
    
    public QuizDTO toDTO(Quiz quiz) {
        if (quiz == null) {
            return null;
        }
        
        return new QuizDTO(
            quiz.getId(),
            quiz.getTitle(),
            quiz.getDescription(),
            quiz.getUser().getId(),
            quiz.getUser().getUsername(),
            quiz.getCreatedAt(),
            quiz.getUpdatedAt()
        );
    }

    public Quiz toEntity(CreateQuizDTO dto) {
        if (dto == null) {
            return null;
        }

        Quiz quiz = new Quiz();
        quiz.setTitle(dto.title());
        quiz.setDescription(dto.description());
        return quiz;
    }
} 