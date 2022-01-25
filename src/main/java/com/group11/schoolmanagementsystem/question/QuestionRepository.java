package com.group11.schoolmanagementsystem.question;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Optional<List<Question>> findQuestionsByTask_Id(Long taskId);

    @Query(value = "SELECT q FROM Question q WHERE q.id IN :ids")
    Optional<List<Question>> findQuestionsByIdList(@Param("ids") Collection<Long> ids);
}
