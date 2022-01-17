package com.group11.schoolmanagementsystem.teacher;

import com.group11.schoolmanagementsystem.department.Department;
import com.group11.schoolmanagementsystem.enums.SubjectDepartment;
import com.group11.schoolmanagementsystem.school.School;
import com.group11.schoolmanagementsystem.subject.Subject;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column()
    private String middleName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

//    @ManyToMany
//    @JoinTable(
//            name = "subject_teacher",
//            joinColumns = @JoinColumn(name="teacher_id"),
//            inverseJoinColumns = @JoinColumn(name = "subject_id")
//    )
//    private List<Subject> subjects;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;
}
