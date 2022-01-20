package com.group11.schoolmanagementsystem.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<List<Task>> findTasksBySection_IdAndSubject_Id(Long sectionId, Long subjectId);
}
