package com.group11.schoolmanagementsystem.question;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Optional<List<Question>> findQuestionsByTask_Id(Long taskId);
}
