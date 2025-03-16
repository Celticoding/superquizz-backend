package com.quiz.repository;

import com.quiz.model.Question;
import com.quiz.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByQuizOrderById(Quiz quiz);
    void deleteByQuiz(Quiz quiz);
} 