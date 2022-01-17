package com.group11.schoolmanagementsystem.material;

import com.group11.schoolmanagementsystem.task.Task;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String filePath;

//    @Lob
//    private byte[] data;

    @ManyToOne()
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Task task;
}
