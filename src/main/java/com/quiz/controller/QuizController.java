package com.quiz.controller;

import com.quiz.dto.CreateQuizDTO;
import com.quiz.dto.QuizDTO;
import com.quiz.mapper.QuizMapper;
import com.quiz.service.QuizService;
import com.quiz.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;
    private final UserService userService;
    private final QuizMapper quizMapper;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public QuizDTO createQuiz(@Valid @RequestBody CreateQuizDTO createQuizDTO) {
        return quizService.createQuiz(createQuizDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @securityService.isQuizCreator(#id)")
    public QuizDTO updateQuiz(@PathVariable Long id, @Valid @RequestBody CreateQuizDTO updateQuizDTO) {
        return quizService.updateQuiz(id, updateQuizDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @securityService.isQuizCreator(#id)")
    public void deleteQuiz(@PathVariable Long id) {
        quizService.deleteQuiz(id);
    }

    @GetMapping("/{id}")
    public QuizDTO getQuiz(@PathVariable Long id) {
        return quizService.getQuizById(id);
    }

    @GetMapping("/user/{userId}")
    public Page<QuizDTO> getUserQuizzes(@PathVariable Long userId, Pageable pageable) {
        return quizService.getQuizzesByUser(userId, pageable);
    }

    @GetMapping("/search")
    public Page<QuizDTO> searchQuizzes(@RequestParam String title, Pageable pageable) {
        return quizService.searchQuizzes(title, pageable);
    }
} 