package com.quiz.service;

import com.quiz.dto.AnswerDTO;
import com.quiz.dto.CreateAnswerDTO;
import com.quiz.mapper.AnswerMapper;
import com.quiz.model.Answer;
import com.quiz.model.Question;
import com.quiz.model.User;
import com.quiz.repository.AnswerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionService questionService;
    private final UserService userService;
    private final AnswerMapper answerMapper;

    public AnswerDTO submitAnswer(Long questionId, String username, CreateAnswerDTO createAnswerDTO) {
        Question question = questionService.findQuestionEntityById(questionId);
        User user = userService.getUserByUsername(username);

        Answer answer = new Answer();
        answer.setQuestion(question);
        answer.setUser(user);
        answer.setSelectedOption(createAnswerDTO.getSelectedOption());
        answer.setCorrect(createAnswerDTO.getSelectedOption().equals(question.getCorrectAnswer()));

        Answer savedAnswer = answerRepository.save(answer);
        return answerMapper.toDTO(savedAnswer);
    }

    public List<AnswerDTO> getUserAnswersForQuestion(Long questionId, Long userId) {
        Question question = questionService.findQuestionEntityById(questionId);
        User user = userService.getUserById(userId);
        
        return answerRepository.findByQuestionAndUser(question, user).stream()
            .map(answerMapper::toDTO)
            .toList();
    }

    public long calculateQuizScore(Long quizId, Long userId) {
        return answerRepository.countCorrectAnswersByQuizAndUser(quizId, userId);
    }

    public void deleteAnswer(Long id) {
        if (!answerRepository.existsById(id)) {
            throw new EntityNotFoundException("Answer not found");
        }
        answerRepository.deleteById(id);
    }

    public void deleteAnswersByQuestion(Question question) {
        answerRepository.deleteByQuestion(question);
    }
} 