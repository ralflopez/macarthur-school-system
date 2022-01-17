package com.group11.schoolmanagementsystem.subject;

import com.group11.schoolmanagementsystem.enums.SubjectDepartment;
import com.group11.schoolmanagementsystem.section.Section;
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
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private int grade;

//    @ManyToMany(mappedBy = "subjects")
//    private List<Teacher> teachers;
}
