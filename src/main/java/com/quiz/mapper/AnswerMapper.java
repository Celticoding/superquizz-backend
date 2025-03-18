package com.quiz.mapper;

import com.quiz.dto.AnswerDTO;
import com.quiz.dto.CreateAnswerDTO;
import com.quiz.model.Answer;
import org.springframework.stereotype.Component;

@Component
public class AnswerMapper {
    
    public AnswerDTO toDTO(Answer answer) {
        if (answer == null) {
            return null;
        }
        
        return new AnswerDTO(
            answer.getId(),
            answer.getSelectedOption(),
            answer.getQuestion().getId(),
            answer.getUser().getId(),
            answer.getUser().getUsername(),
            answer.getCreatedAt(),
            answer.getUpdatedAt()
        );
    }

    public Answer toEntity(CreateAnswerDTO dto) {
        if (dto == null) {
            return null;
        }

        Answer answer = new Answer();
        answer.setSelectedOption(dto.selectedOption());
        return answer;
    }
} 