package com.group11.schoolmanagementsystem.grade;

import com.group11.schoolmanagementsystem.student.Student;
import com.group11.schoolmanagementsystem.task.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
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

    @ManyToOne()
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Task task;

    private float value;
}
