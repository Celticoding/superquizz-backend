package com.quiz.service;

import com.quiz.dto.CreateQuizDTO;
import com.quiz.dto.QuizDTO;
import com.quiz.mapper.QuizMapper;
import com.quiz.model.Quiz;
import com.quiz.model.User;
import com.quiz.repository.QuizRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class QuizService {

    private final QuizRepository quizRepository;
    private final UserService userService;
    private final QuizMapper quizMapper;

    public QuizDTO createQuiz(CreateQuizDTO createQuizDTO, String username) {
        User user = userService.getUserByUsername(username);
        
        Quiz quiz = new Quiz();
        quiz.setTitle(createQuizDTO.getTitle());
        quiz.setDescription(createQuizDTO.getDescription());
        quiz.setCreatedBy(user);
        
        Quiz savedQuiz = quizRepository.save(quiz);
        return quizMapper.toDTO(savedQuiz);
    }

    public QuizDTO updateQuiz(Long id, CreateQuizDTO updateQuizDTO) {
        Quiz quiz = findQuizEntityById(id);

        quiz.setTitle(updateQuizDTO.getTitle());
        quiz.setDescription(updateQuizDTO.getDescription());

        Quiz updatedQuiz = quizRepository.save(quiz);
        return quizMapper.toDTO(updatedQuiz);
    }

    public void deleteQuiz(Long id) {
        if (!quizRepository.existsById(id)) {
            throw new EntityNotFoundException("Quiz not found");
        }
        quizRepository.deleteById(id);
    }

    public QuizDTO getQuizById(Long id) {
        return quizMapper.toDTO(findQuizEntityById(id));
    }

    public Page<QuizDTO> getQuizzesByUser(Long userId, Pageable pageable) {
        User user = userService.getUserById(userId);
        return quizRepository.findByCreatedBy(user, pageable)
            .map(quizMapper::toDTO);
    }

    public Page<QuizDTO> searchQuizzes(String title, Pageable pageable) {
        return quizRepository.findByTitleContainingIgnoreCase(title, pageable)
            .map(quizMapper::toDTO);
    }

    // Méthode interne pour obtenir l'entité Quiz
    Quiz findQuizEntityById(Long id) {
        return quizRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Quiz not found"));
    }
} 