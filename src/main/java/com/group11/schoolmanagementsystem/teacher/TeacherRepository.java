package com.group11.schoolmanagementsystem.teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    @Query(value = "SELECT t FROM Teacher t WHERE t.department.id = ?1")
    Optional<List<Teacher>> findByAllByDepartmentId(Long deptId);

    Optional<Teacher> findTeacherByUsername(String username);
}
