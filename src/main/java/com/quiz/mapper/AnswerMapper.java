package com.quiz.mapper;

import com.quiz.dto.AnswerDTO;
import com.quiz.model.Answer;
import org.springframework.stereotype.Component;

@Component
public class AnswerMapper {
    
    public AnswerDTO toDTO(Answer answer) {
        if (answer == null) {
            return null;
        }

        AnswerDTO dto = new AnswerDTO();
        dto.setId(answer.getId());
        dto.setQuestionId(answer.getQuestion().getId());
        dto.setUserId(answer.getUser().getId());
        dto.setSelectedOption(answer.getSelectedOption());
        dto.setCorrect(answer.isCorrect());
        return dto;
    }

    public Answer toEntity(AnswerDTO dto) {
        if (dto == null) {
            return null;
        }

        Answer answer = new Answer();
        answer.setId(dto.getId());
        answer.setSelectedOption(dto.getSelectedOption());
        answer.setCorrect(dto.isCorrect());
        return answer;
    }
} 