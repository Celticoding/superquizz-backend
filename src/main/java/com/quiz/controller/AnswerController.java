package com.quiz.controller;

import com.quiz.dto.AnswerDTO;
import com.quiz.dto.CreateAnswerDTO;
import com.quiz.service.AnswerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/answers")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping("/question/{questionId}")
    @PreAuthorize("isAuthenticated()")
    public AnswerDTO submitAnswer(@PathVariable Long questionId,
                                @Valid @RequestBody CreateAnswerDTO createAnswerDTO) {
        return answerService.submitAnswer(questionId, createAnswerDTO);
    }

    @GetMapping("/question/{questionId}/user/{userId}")
    @PreAuthorize("hasRole('ADMIN') or @securityService.isCurrentUser(#userId)")
    public List<AnswerDTO> getUserAnswersForQuestion(@PathVariable Long questionId,
                                                   @PathVariable Long userId) {
        return answerService.getUserAnswersForQuestion(questionId, userId);
    }

    @GetMapping("/quiz/{quizId}/score/{userId}")
    @PreAuthorize("hasRole('ADMIN') or @securityService.isCurrentUser(#userId)")
    public long getQuizScore(@PathVariable Long quizId,
                            @PathVariable Long userId) {
        return answerService.calculateQuizScore(quizId, userId);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAnswer(@PathVariable Long id) {
        answerService.deleteAnswer(id);
    }
} 