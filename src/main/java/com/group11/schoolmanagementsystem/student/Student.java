package com.group11.schoolmanagementsystem.student;

import com.group11.schoolmanagementsystem.enums.Gender;
import com.group11.schoolmanagementsystem.school.School;
import com.group11.schoolmanagementsystem.section.Section;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "lrn", nullable = false)
    private Long lrn;

    @Column(nullable = false)
    private String firstName;

    @Column()
    private String middleName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private int birthMonth;

    @Column(nullable = false)
    private int birthDay;

    @Column(nullable = false)
    private int birthYear;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToOne()
    @JoinColumn(name = "section_id", referencedColumnName = "id")
    private Section section;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;
}
