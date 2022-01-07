package com.group11.schoolmanagementsystem.student;

import com.group11.schoolmanagementsystem.enums.Gender;
import com.group11.schoolmanagementsystem.section.Section;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "lrn", nullable = false)
    private Long lrn;

    @Column(nullable = false)
    private String first_name;

    @Column()
    private String middle_name;

    @Column(nullable = false)
    private String last_name;

    @Column(nullable = false)
    private int birth_month;

    @Column(nullable = false)
    private int birth_day;

    @Column(nullable = false)
    private int birth_year;

    @Column(nullable = false)
    private Gender gender;

    @ManyToOne()
    @JoinColumn(name = "section_id", referencedColumnName = "id")
    private Section section;
}
