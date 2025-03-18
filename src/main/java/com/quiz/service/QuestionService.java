package com.quiz.service;

import com.quiz.dto.QuestionDTO;
import com.quiz.dto.CreateQuestionDTO;
import com.quiz.mapper.QuestionMapper;
import com.quiz.model.Question;
import com.quiz.model.Quiz;
import com.quiz.repository.QuestionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuizService quizService;
    private final QuestionMapper questionMapper;

    @Transactional
    public QuestionDTO createQuestion(Long quizId, CreateQuestionDTO createQuestionDTO) {
        Quiz quiz = quizService.findQuizEntityById(quizId);
        
        Question question = questionMapper.toEntity(createQuestionDTO);
        question.setQuiz(quiz);
        return questionMapper.toDTO(questionRepository.save(question));
    }

    @Transactional
    public QuestionDTO updateQuestion(Long id, CreateQuestionDTO updateQuestionDTO) {
        Question question = findQuestionEntityById(id);
        question.setQuestionContent(updateQuestionDTO.questionContent());
        question.setCorrectAnswer(updateQuestionDTO.correctAnswer());
        question.setOptions(updateQuestionDTO.options());
        return questionMapper.toDTO(questionRepository.save(question));
    }

    @Transactional
    public void deleteQuestion(Long id) {
        if (!questionRepository.existsById(id)) {
            throw new EntityNotFoundException("Question non trouvée");
        }
        questionRepository.deleteById(id);
    }

    public QuestionDTO getQuestionById(Long id) {
        return questionMapper.toDTO(findQuestionEntityById(id));
    }

    public List<QuestionDTO> getQuestionsByQuiz(Long quizId) {
        Quiz quiz = quizService.findQuizEntityById(quizId);
        return questionRepository.findByQuizOrderById(quiz).stream()
            .map(questionMapper::toDTO)
            .toList();
    }

    public void deleteQuestionsByQuiz(Quiz quiz) {
        questionRepository.deleteByQuiz(quiz);
    }

    // Méthode interne pour obtenir l'entité Question
    Question findQuestionEntityById(Long id) {
        return questionRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Question non trouvée"));
    }
} 