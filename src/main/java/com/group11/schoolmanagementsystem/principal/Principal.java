package com.group11.schoolmanagementsystem.principal;

import com.group11.schoolmanagementsystem.school.School;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "principal")
public class Principal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column()
    private String middleName;

    @Column(nullable = false)
    private String lastName;

    @OneToOne()
    @JoinColumn(name = "school_id", referencedColumnName = "id")
    private School school;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;
}
