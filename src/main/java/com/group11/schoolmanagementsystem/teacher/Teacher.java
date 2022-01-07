package com.group11.schoolmanagementsystem.teacher;

import com.group11.schoolmanagementsystem.school.School;
import com.group11.schoolmanagementsystem.section.Section;
import com.group11.schoolmanagementsystem.subject.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String first_name;

    @Column()
    private String middle_name;

    @Column(nullable = false)
    private String last_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", referencedColumnName = "id")
    private School school;

    @OneToMany(mappedBy = "teacher")
    private List<Subject> sections;
}
