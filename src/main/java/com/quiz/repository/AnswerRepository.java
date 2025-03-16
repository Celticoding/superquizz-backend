package com.quiz.repository;

import com.quiz.model.Answer;
import com.quiz.model.Question;
import com.quiz.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByQuestionAndUser(Question question, User user);
    
    @Query("SELECT COUNT(a) FROM Answer a WHERE a.question.quiz.id = ?1 AND a.user.id = ?2 AND a.isCorrect = true")
    long countCorrectAnswersByQuizAndUser(Long quizId, Long userId);
    
    void deleteByQuestion(Question question);
} 