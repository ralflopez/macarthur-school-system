package com.group11.schoolmanagementsystem.department;

import com.group11.schoolmanagementsystem.teacher.Teacher;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "head_teacher_id", referencedColumnName = "id")
    private Teacher head;

    @OneToMany(mappedBy = "department")
    private List<Teacher> teachers;
}
