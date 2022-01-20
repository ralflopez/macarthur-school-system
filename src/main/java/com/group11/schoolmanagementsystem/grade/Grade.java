package com.group11.schoolmanagementsystem.grade;

import com.group11.schoolmanagementsystem.question.Question;
import com.group11.schoolmanagementsystem.student.Student;
import com.group11.schoolmanagementsystem.task.Task;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "student_id", referencedColumnName = "lrn")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private Question question;

    private int score;
}
