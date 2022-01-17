package com.group11.schoolmanagementsystem.school;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "school")
public class School {
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String region;

    @Column(nullable = false)
    private String division;

    @Column(nullable = false)
    private String district;
}
