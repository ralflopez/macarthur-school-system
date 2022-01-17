package com.group11.schoolmanagementsystem.section;

import com.group11.schoolmanagementsystem.school.School;
import com.group11.schoolmanagementsystem.student.Student;
import com.group11.schoolmanagementsystem.subject.Subject;
import com.group11.schoolmanagementsystem.teacher.Teacher;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int grade;

    @Column(nullable = false)
    private String name;

    @OneToOne()
    @JoinColumn(name = "adviser_id", referencedColumnName = "id")
    private Teacher adviser;

    @OneToMany(mappedBy = "section")
    private List<Student> students;
}
