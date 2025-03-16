package com.quiz.service;

import com.quiz.dto.CreateQuestionDTO;
import com.quiz.dto.QuestionDTO;
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
@Transactional
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuizService quizService;
    private final QuestionMapper questionMapper;

    public QuestionDTO createQuestion(Long quizId, CreateQuestionDTO createQuestionDTO) {
        Quiz quiz = quizService.findQuizEntityById(quizId);
        
        Question question = new Question();
        question.setQuiz(quiz);
        question.setContent(createQuestionDTO.getContent());
        question.setOptions(createQuestionDTO.getOptions());
        question.setCorrectAnswer(createQuestionDTO.getCorrectAnswer());

        Question savedQuestion = questionRepository.save(question);
        return questionMapper.toDTO(savedQuestion);
    }

    public QuestionDTO updateQuestion(Long id, CreateQuestionDTO updateQuestionDTO) {
        Question question = questionRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Question not found"));

        question.setContent(updateQuestionDTO.getContent());
        question.setOptions(updateQuestionDTO.getOptions());
        question.setCorrectAnswer(updateQuestionDTO.getCorrectAnswer());

        Question updatedQuestion = questionRepository.save(question);
        return questionMapper.toDTO(updatedQuestion);
    }

    public void deleteQuestion(Long id) {
        if (!questionRepository.existsById(id)) {
            throw new EntityNotFoundException("Question not found");
        }
        questionRepository.deleteById(id);
    }

    public QuestionDTO getQuestionById(Long id) {
        Question question = questionRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Question not found"));
        return questionMapper.toDTO(question);
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
            .orElseThrow(() -> new EntityNotFoundException("Question not found"));
    }
} 