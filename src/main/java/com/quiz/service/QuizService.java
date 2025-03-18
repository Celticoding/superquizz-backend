package com.quiz.service;

import com.quiz.dto.QuizDTO;
import com.quiz.dto.CreateQuizDTO;
import com.quiz.mapper.QuizMapper;
import com.quiz.model.Quiz;
import com.quiz.model.User;
import com.quiz.repository.QuizRepository;
import com.quiz.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    private final UserService userService;
    private final QuizMapper quizMapper;

    @Transactional
    public QuizDTO createQuiz(CreateQuizDTO createQuizDTO) {
        User user = userService.getUserById(createQuizDTO.userId());

        Quiz quiz = quizMapper.toEntity(createQuizDTO);
        quiz.setUser(user);
        return quizMapper.toDTO(quizRepository.save(quiz));
    }

    @Transactional
    public QuizDTO updateQuiz(Long id, CreateQuizDTO updateQuizDTO) {
        Quiz quiz = findQuizEntityById(id);
        quiz.setTitle(updateQuizDTO.title());
        quiz.setDescription(updateQuizDTO.description());
        return quizMapper.toDTO(quizRepository.save(quiz));
    }

    @Transactional
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

    public Page<QuizDTO> searchQuizzes(String query, Pageable pageable) {
        return quizRepository.findByTitleContainingIgnoreCase(query, pageable)
            .map(quizMapper::toDTO);
    }

    Quiz findQuizEntityById(Long id) {
        return quizRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Quiz non trouvé"));
    }
} 