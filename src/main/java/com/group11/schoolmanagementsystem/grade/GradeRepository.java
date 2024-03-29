package com.group11.schoolmanagementsystem.grade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
}
/*
-- TOTAL SCORE OF STUDENT BY TASK
SELECT TASK_ID, STUDENT_ID, SUM(SCORE) AS STUDENT_SCORE, SUM(POINT) AS TOTAL_SCORE, (SUM(SCORE) / SUM(POINT)) * 100 AS AVERAGE
FROM GRADE
JOIN QUESTION ON QUESTION.ID = GRADE.QUESTION_ID
GROUP BY STUDENT_ID, TASK_ID;

-- TOTAL SCORE FOR FOR EVERY QUARTER
SELECT STUDENT_ID, SUM(SCORE) AS STUDENT_SCORE, SUM(POINT) AS TOTAL_SCORE, (SUM(SCORE) / SUM (POINT)) * 100 AS AVERAGE, TYPE, QUARTER
FROM GRADE
JOIN QUESTION ON QUESTION.ID = GRADE.QUESTION_ID
JOIN TASK ON QUESTION.TASK_ID = TASK.ID
GROUP BY STUDENT_ID, TYPE, QUARTER;
* */