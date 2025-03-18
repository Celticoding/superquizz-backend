package com.quiz.mapper;

import com.quiz.dto.QuestionDTO;
import com.quiz.dto.CreateQuestionDTO;
import com.quiz.model.Question;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper {
    
    public QuestionDTO toDTO(Question question) {
        if (question == null) {
            return null;
        }
        
        return new QuestionDTO(
            question.getId(),
            question.getQuestionContent(),
            question.getCorrectAnswer(),
            question.getQuiz().getId(),
            question.getCreatedAt(),
            question.getUpdatedAt()
        );
    }

    public Question toEntity(CreateQuestionDTO dto) {
        if (dto == null) {
            return null;
        }

        Question question = new Question();
        question.setQuestionContent(dto.questionContent());
        question.setCorrectAnswer(dto.correctAnswer());
        question.setOptions(dto.options());
        return question;
    }
} 