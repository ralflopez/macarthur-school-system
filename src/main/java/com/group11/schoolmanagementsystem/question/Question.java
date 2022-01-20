package com.group11.schoolmanagementsystem.question;

import com.group11.schoolmanagementsystem.enums.QuestionType;
import com.group11.schoolmanagementsystem.task.Task;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Task task;

    @Column(nullable = false)
    private String question;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers;

    @Column(nullable = false)
    private int point = 1;

    @OneToMany(mappedBy = "question")
    private List<Choice> choices;

    @Enumerated(EnumType.STRING)
    private QuestionType type;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Question> grades;
}
