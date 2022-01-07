package com.group11.schoolmanagementsystem.principal;

import com.group11.schoolmanagementsystem.school.School;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "principal")
public class Principal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String first_name;

    @Column()
    private String middle_name;

    @Column(nullable = false)
    private String last_name;

    @OneToOne()
    @JoinColumn(name = "school_id", referencedColumnName = "id")
    private School school;
}
