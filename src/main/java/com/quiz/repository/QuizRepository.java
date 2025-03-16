package com.quiz.repository;

import com.quiz.model.Quiz;
import com.quiz.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    Page<Quiz> findByCreatedBy(User user, Pageable pageable);
    Page<Quiz> findByTitleContainingIgnoreCase(String title, Pageable pageable);
} 