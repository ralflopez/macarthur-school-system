package com.group11.schoolmanagementsystem.section;

import com.group11.schoolmanagementsystem.subject.Subject;
import com.group11.schoolmanagementsystem.teacher.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
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

    @Column(nullable = false, name = "school_year")
    private int schoolYear;

    @OneToOne()
    @JoinColumn(name = "adviser_id", referencedColumnName = "id")
    private Teacher adviser;

    @OneToMany(mappedBy = "section")
    private List<Subject> subjects;
}
