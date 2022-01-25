package com.group11.schoolmanagementsystem.question;

import com.group11.schoolmanagementsystem.student.Student;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", referencedColumnName = "lrn")
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private Question question;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "choice_id", referencedColumnName = "id")
    private Choice choice;

    @Column
    private boolean isCorrect;
}
