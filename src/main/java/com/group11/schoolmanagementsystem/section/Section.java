package com.group11.schoolmanagementsystem.section;

import com.group11.schoolmanagementsystem.school.School;
import com.group11.schoolmanagementsystem.subject.Subject;
import com.group11.schoolmanagementsystem.teacher.Teacher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
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

    @ManyToMany
    @JoinTable(
            name = "subject_section",
            joinColumns = @JoinColumn(name="section_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<Subject> subjects;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", referencedColumnName = "id")
    private School school;
}
