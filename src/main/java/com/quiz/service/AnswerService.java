package com.quiz.service;

import com.quiz.dto.AnswerDTO;
import com.quiz.dto.CreateAnswerDTO;
import com.quiz.dto.QuestionDTO;
import com.quiz.mapper.AnswerMapper;
import com.quiz.model.Answer;
import com.quiz.model.Question;
import com.quiz.model.User;
import com.quiz.repository.AnswerRepository;
import com.quiz.repository.QuestionRepository;
import com.quiz.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final UserService userService;
    private final QuestionService questionService;
    private final AnswerMapper answerMapper;

    @Transactional
    public AnswerDTO submitAnswer(Long questionId, CreateAnswerDTO createAnswerDTO) {
        Question question = questionService.findQuestionEntityById(questionId);

        User user = userService.getUserById(createAnswerDTO.userId());

        Answer answer = answerMapper.toEntity(createAnswerDTO);
        answer.setQuestion(question);
        answer.setUser(user);
        return answerMapper.toDTO(answerRepository.save(answer));
    }

    public List<AnswerDTO> getUserAnswersForQuestion(Long questionId, Long userId) {
        Question question = questionService.findQuestionEntityById(questionId);

        User user = userService.getUserById(userId);

        return answerRepository.findByQuestionAndUser(question, user)
            .stream()
            .map(answerMapper::toDTO)
            .toList();
    }

    public long calculateQuizScore(Long quizId, Long userId) {
        return answerRepository.countCorrectAnswersByQuizAndUser(quizId, userId);
    }

    @Transactional
    public void deleteAnswer(Long id) {
        if (!answerRepository.existsById(id)) {
            throw new EntityNotFoundException("Réponse non trouvée");
        }
        answerRepository.deleteById(id);
    }

    @Transactional
    public void deleteAnswersByQuestion(Long questionId) {
        Question question = questionService.findQuestionEntityById(questionId);
        answerRepository.deleteByQuestion(question);
    }
} 