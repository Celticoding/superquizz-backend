package com.quiz.mapper;

import com.quiz.dto.QuestionDTO;
import com.quiz.model.Question;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper {
    
    public QuestionDTO toDTO(Question question) {
        if (question == null) {
            return null;
        }

        QuestionDTO dto = new QuestionDTO();
        dto.setId(question.getId());
        dto.setQuizId(question.getQuiz().getId());
        dto.setContent(question.getContent());
        dto.setOptions(question.getOptions());
        return dto;
    }

    public Question toEntity(QuestionDTO dto) {
        if (dto == null) {
            return null;
        }

        Question question = new Question();
        question.setId(dto.getId());
        question.setContent(dto.getContent());
        question.setOptions(dto.getOptions());
        return question;
    }
} 