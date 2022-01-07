package com.group11.schoolmanagementsystem.material;

import com.group11.schoolmanagementsystem.task.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;

    @Lob
    private byte[] data;

    @ManyToOne()
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Task task;
}
