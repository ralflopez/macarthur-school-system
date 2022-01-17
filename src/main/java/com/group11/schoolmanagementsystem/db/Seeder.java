package com.group11.schoolmanagementsystem.db;

import com.group11.schoolmanagementsystem.department.Department;
import com.group11.schoolmanagementsystem.department.DepartmentRepository;
import com.group11.schoolmanagementsystem.department.dto.DepartmentDto;
import com.group11.schoolmanagementsystem.enums.Gender;
import com.group11.schoolmanagementsystem.enums.QuestionType;
import com.group11.schoolmanagementsystem.enums.SubjectDepartment;
import com.group11.schoolmanagementsystem.enums.TaskType;
import com.group11.schoolmanagementsystem.grade.Grade;
import com.group11.schoolmanagementsystem.grade.GradeRepository;
import com.group11.schoolmanagementsystem.principal.Principal;
import com.group11.schoolmanagementsystem.principal.PrincipalRepository;
import com.group11.schoolmanagementsystem.question.Answer;
import com.group11.schoolmanagementsystem.question.Choice;
import com.group11.schoolmanagementsystem.question.Question;
import com.group11.schoolmanagementsystem.question.QuestionRepository;
import com.group11.schoolmanagementsystem.school.School;
import com.group11.schoolmanagementsystem.school.SchoolRepository;
import com.group11.schoolmanagementsystem.section.Section;
import com.group11.schoolmanagementsystem.section.SectionRepository;
import com.group11.schoolmanagementsystem.student.Student;
import com.group11.schoolmanagementsystem.student.StudentRepository;
import com.group11.schoolmanagementsystem.subject.Subject;
import com.group11.schoolmanagementsystem.subject.SubjectRepository;
import com.group11.schoolmanagementsystem.subject_section.SubjectSection;
import com.group11.schoolmanagementsystem.subject_section.SubjectSectionKey;
import com.group11.schoolmanagementsystem.subject_section.SubjectSectionRepository;
import com.group11.schoolmanagementsystem.task.Task;
import com.group11.schoolmanagementsystem.task.TaskRepository;
import com.group11.schoolmanagementsystem.teacher.Teacher;
import com.group11.schoolmanagementsystem.teacher.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class Seeder {
    private SchoolRepository schoolRepository;
    private PrincipalRepository principalRepository;
    private TeacherRepository teacherRepository;
    private StudentRepository studentRepository;
    private SectionRepository sectionRepository;
    private SubjectRepository subjectRepository;
    private TaskRepository taskRepository;
    private GradeRepository gradeRepository;
    private DepartmentRepository departmentRepository;
    private QuestionRepository questionRepository;
    private SubjectSectionRepository subjectSectionRepository;

    private School school;
    private List<String> subjects = List.of("English", "Science", "Mathematics", "Filipino", "ESP", "MAPEH", "Mother Tounge", "Araling Panlipunan");
    List<Department> departments = new ArrayList<>();
    List<Teacher> teachers = new ArrayList<>();
    List<Section> sections = new ArrayList<>();
    List<Student> students = new ArrayList<>();
    List<Subject> subjectsEntities = new ArrayList<>();
    List<Task> tasks = new ArrayList<>();
    List<Question> questions = new ArrayList<>();

    @Autowired
    public Seeder(SchoolRepository schoolRepository, PrincipalRepository principalRepository, TeacherRepository teacherRepository, StudentRepository studentRepository, SectionRepository sectionRepository, SubjectRepository subjectRepository, TaskRepository taskRepository, GradeRepository gradeRepository, DepartmentRepository departmentRepository, QuestionRepository questionRepository, SubjectSectionRepository subjectSectionRepository) {
        this.schoolRepository = schoolRepository;
        this.principalRepository = principalRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.sectionRepository = sectionRepository;
        this.subjectRepository = subjectRepository;
        this.taskRepository = taskRepository;
        this.gradeRepository = gradeRepository;
        this.departmentRepository = departmentRepository;
        this.questionRepository = questionRepository;
        this.subjectSectionRepository = subjectSectionRepository;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        schoolRepository.deleteAll();
        principalRepository.deleteAll();
        subjectRepository.deleteAll();
        teacherRepository.deleteAll();
        sectionRepository.deleteAll();
        taskRepository.deleteAll();
        departmentRepository.deleteAll();
        questionRepository.deleteAll();
        gradeRepository.deleteAll();
        subjectSectionRepository.deleteAll();
        taskRepository.deleteAll();
        questionRepository.deleteAll();
        gradeRepository.deleteAll();

        seedSchool();
        seedPrincipal();
        seedDepartment();
        seedSubject();
        seedTeacher();
        seedHeadTeacher();
        seedSection();
        seedStudent();
        seedSubjectSectionTeacher();
        seedTask();
        seedQuestions();
        seedGrade();

        System.out.println("SEEDING DONE!!!!");
    }

    public void seedSchool() {
        this.school = new School(Long.valueOf(122474), "Alang-Alang Primary School", "VIII", "Eastern Samar", "General Macarthur");
        schoolRepository.save(school);
    }

    public void seedPrincipal() {
        principalRepository.save(
                new Principal(Long.valueOf(1), "Principal", "Princiapl", "User", school, "principal", "password")
        );
    }

    public void seedDepartment() {
        for (int i = 0; i < subjects.size(); i++) {
            departments.add(
                    new Department(Long.valueOf(i + 1), subjects.get(i), null, null)
            );
        }

        departmentRepository.saveAll(departments);
    }

    public void seedSubject() {
        for (int i = 0; i < subjects.size(); i++) {
            subjectsEntities.add(
                    new Subject(
                            Long.valueOf(i + 1),
                            subjects.get(i),
                            1
//                            null
                    )
            );
        }

        subjectRepository.saveAll(subjectsEntities);
    }

    public void seedTeacher() {
        for (int i = 0; i < subjects.size(); i++) {
            teachers.add(
                    new Teacher(
                            Long.valueOf(i + 1),
                            subjects.get(i),
                            "Middle", "Teacher",
                            subjects.get(i) + "Teacher123",
                            "password",
//                            null,
                            departments.get(i))
            );
        }
        teacherRepository.saveAll(teachers);
    }

    public void seedHeadTeacher() {
        for (int i = 0; i < departments.size(); i++) {
            Department department = departments.get(i);
            department.setHead(teachers.get(i));
            departmentRepository.save(department);
        }
    }

    public void seedSection() {
        for (int i = 0; i < 3; i++) {
            sections.add(
              new Section(Long.valueOf(i + 1), 1, String.valueOf(i + 1), teachers.get(i), null)
            );
        }

        sectionRepository.saveAll(sections);
    }

    public void seedStudent() {
        Random random = new Random();
        students.add(new Student(Long.valueOf(12200000), "Test", "Student", "Student", 1, 1, 2016, Gender.MALE, sections.get(0), "test", "password"));
        for (int i = 1; i < 30; i++) {
            int randomDay = (int)Math.floor(Math.random()*(30-1+1)+1);
            int randomMonth = (int)Math.floor(Math.random()*(12-1+1)+1);
            int randomYear = (int)Math.floor(Math.random()*(2017-2016+2016)+2016);
            boolean gender = random.nextBoolean();

            students.add(
                    new Student(12200000 + Long.valueOf(i), "Student" + (i), "Student", "Student" + (i), randomMonth, randomDay, randomYear, Gender.valueOf(gender ? "MALE" : "FEMALE"), sections.get(0), "student" + (i), "password")
            );
        }

        studentRepository.saveAll(students);
    }

    public void seedSubjectSectionTeacher() {
        List<SubjectSection> subjectSections = new ArrayList<>();
        for (int i = 0; i < sections.size(); i++) {
            for (int j = 0; j < subjects.size(); j++) {
                subjectSections.add(
                    new SubjectSection(
                            new SubjectSectionKey(subjectsEntities.get(j).getId(), sections.get(i).getId()),
                            subjectsEntities.get(j),
                            sections.get(i),
                            teachers.get(j)
                    )
                );
            }
        }

        subjectSectionRepository.saveAll(subjectSections);
    }

    public void seedTask() {

        for (int i = 0; i < subjects.size(); i++) {
            Task task = Task.builder()
                    .name("Introduction to " + subjectsEntities.get(i).getName())
                    .quarter(1)
                    .type(TaskType.WRITTEN)
                    .teacher(teachers.get(i))
                    .subject(subjectsEntities.get(i))
                    .section(sections.get(0))
                    .build();
            tasks.add(task);
        }

        taskRepository.saveAll(tasks);
    }

    public void seedQuestions() {
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            Question question = Question.builder()
                    .question("What is " + t.getSubject().getName() + "?")
                    .task(t)
                    .point(1)
                    .build();

            Answer answer = Answer.builder()
                    .question(question)
                    .answer("")
                    .type(QuestionType.OPEN)
                    .build();

            question.setAnswers(List.of(answer));
            questions.add(question);

            Question question1 = Question.builder()
                    .task(t)
                    .point(1)
                    .question("What is this subject?")
                    .build();

            Choice choice11 = Choice.builder()
                    .choice(subjects.get(i))
                    .question(question1)
                    .build();

            Choice choice12 = Choice.builder()
                    .choice("Classroom")
                    .question(question1)
                    .build();

            Choice choice13 = Choice.builder()
                    .choice("Wrong Answer")
                    .question(question1)
                    .build();

            question1.setChoices(List.of(choice11, choice12, choice13));

            Answer answer1 = Answer.builder()
                    .question(question1)
                    .type(QuestionType.ALPHA)
                    .answer("a")
                    .build();

            question1.setAnswers(List.of(answer1));
            questions.add(question1);
        }

        questionRepository.saveAll(questions);
    }

    public void seedGrade() {
        List<Grade> grades = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            for (int j = 0; j < questions.size(); j++) {
                Grade grade = Grade.builder()
                        .student(students.get(i))
                        .question(questions.get(j))
                        .score(1)
                        .build();
                grades.add(grade);
            }
        }

        gradeRepository.saveAll(grades);
    }
}
