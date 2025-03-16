package com.quiz.controller;

import com.quiz.dto.CreateQuestionDTO;
import com.quiz.dto.QuestionDTO;
import com.quiz.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("/quiz/{quizId}")
    @PreAuthorize("hasRole('ADMIN') or @securityService.isQuizCreator(#quizId)")
    public QuestionDTO createQuestion(@PathVariable Long quizId,
                                    @Valid @RequestBody CreateQuestionDTO createQuestionDTO) {
        return questionService.createQuestion(quizId, createQuestionDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @securityService.isQuestionCreator(#id)")
    public QuestionDTO updateQuestion(@PathVariable Long id,
                                    @Valid @RequestBody CreateQuestionDTO updateQuestionDTO) {
        return questionService.updateQuestion(id, updateQuestionDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @securityService.isQuestionCreator(#id)")
    public void deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
    }

    @GetMapping("/{id}")
    public QuestionDTO getQuestion(@PathVariable Long id) {
        return questionService.getQuestionById(id);
    }

    @GetMapping("/quiz/{quizId}")
    public List<QuestionDTO> getQuizQuestions(@PathVariable Long quizId) {
        return questionService.getQuestionsByQuiz(quizId);
    }
} 