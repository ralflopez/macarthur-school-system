package com.group11.schoolmanagementsystem.db;

import com.group11.schoolmanagementsystem.enums.SubjectDepartment;
import com.group11.schoolmanagementsystem.enums.TaskType;
import com.group11.schoolmanagementsystem.grade.GradeRepository;
import com.group11.schoolmanagementsystem.principal.Principal;
import com.group11.schoolmanagementsystem.principal.PrincipalRepository;
import com.group11.schoolmanagementsystem.school.School;
import com.group11.schoolmanagementsystem.school.SchoolRepository;
import com.group11.schoolmanagementsystem.section.Section;
import com.group11.schoolmanagementsystem.section.SectionRepository;
import com.group11.schoolmanagementsystem.student.StudentRepository;
import com.group11.schoolmanagementsystem.subject.Subject;
import com.group11.schoolmanagementsystem.subject.SubjectRepository;
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

    @Autowired
    public Seeder(SchoolRepository schoolRepository, PrincipalRepository principalRepository, TeacherRepository teacherRepository, StudentRepository studentRepository, SectionRepository sectionRepository, SubjectRepository subjectRepository, TaskRepository taskRepository, GradeRepository gradeRepository) {
        this.schoolRepository = schoolRepository;
        this.principalRepository = principalRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.sectionRepository = sectionRepository;
        this.subjectRepository = subjectRepository;
        this.taskRepository = taskRepository;
        this.gradeRepository = gradeRepository;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        schoolRepository.deleteAll();
        principalRepository.deleteAll();
        subjectRepository.deleteAll();
        teacherRepository.deleteAll();
        sectionRepository.deleteAll();
        taskRepository.deleteAll();

        seedSchool();
        seedPrincipal();
        seedSubjects();
        seedTeachers();
        seedSections();
        seedTasks();

        System.out.println("SEEDING DONE!!!!");
    }

    private void seedSchool() {
        if (schoolRepository.findById(Long.valueOf(122474)).isPresent()) {
            return;
        }

        School newSchool = School.builder()
                .id(Long.valueOf(122474))
                .name("Alang-Alang Primary School")
                .region("VIII")
                .division("Eastern Samar")
                .district("General Macarthur")
                .build();

        schoolRepository.save(newSchool);
    }

    private void seedPrincipal() {
        if (principalRepository.findById(Long.valueOf(1)).isPresent()) {
            return;
        }

        School school = schoolRepository.findById(Long.valueOf(Long.valueOf(122474))).get();

        Principal principal = Principal.builder()
                .id(Long.valueOf(1))
                .firstName("Principal")
                .middleName("XXX")
                .lastName("ABC")
                .school(school)
                .build();

        principalRepository.save(principal);
    }

    private void seedSubjects() {
        if (!subjectRepository.findAll().isEmpty()) {
            return;
        }

        Subject eng = Subject.builder()
                .name("English")
                .build();

        Subject sci = Subject.builder()
                .name("Science")
                .build();

        Subject math = Subject.builder()
                .name("Math")
                .build();

        Subject fil = Subject.builder()
                .name("Filipino")
                .build();

        Subject esp = Subject.builder()
                .name("Edukasyon sa Pagpapakatao")
                .build();

        Subject mapeh = Subject.builder()
                .name("MAPEH")
                .build();

        Subject mt = Subject.builder()
                .name("Mother Tongue")
                .build();

        Subject ap = Subject.builder()
                .name("Araling Panlipunan")
                .build();

        subjectRepository.saveAll(Arrays.asList(eng, sci, math, fil, esp, mapeh, mt, ap));
    }

    private void seedTeachers() {
        if (!teacherRepository.findAll().isEmpty()) {
            return;
        }

        School school = schoolRepository.findById(Long.valueOf(Long.valueOf(122474))).get();
        List<Subject> subjects = subjectRepository.findAll();

        Teacher english = Teacher.builder()
                .firstName("English")
                .middleName("ABC")
                .lastName("Teacher")
                .school(school)
                .subjects(subjects.stream().filter(s -> s.getName().equals("English")).collect(Collectors.toList()))
                .build();

        Teacher science = Teacher.builder()
                .firstName("Science")
                .middleName("ABC")
                .lastName("Teacher")
                .school(school)
                .subjects(subjects.stream().filter(s -> s.getName().equals("Science")).collect(Collectors.toList()))
                .build();

        Teacher math = Teacher.builder()
                .firstName("Math")
                .middleName("ABC")
                .lastName("Teacher")
                .school(school)
                .subjects(subjects.stream().filter(s -> s.getName().equals("Math")).collect(Collectors.toList()))
                .build();

        Teacher fil = Teacher.builder()
                .firstName("Filipino")
                .middleName("ABC")
                .lastName("Teacher")
                .school(school)
                .subjects(subjects.stream().filter(s -> s.getName().equals("Filipino")).collect(Collectors.toList()))
                .build();

        Teacher esp = Teacher.builder()
                .firstName("ESP")
                .middleName("ABC")
                .lastName("Teacher")
                .school(school)
                .subjects(subjects.stream().filter(s -> s.getName().equals("Edukasyon sa Pagpapakatao")).collect(Collectors.toList()))
                .build();

        Teacher mapeh = Teacher.builder()
                .firstName("Mapeh")
                .middleName("ABC")
                .lastName("Teacher")
                .school(school)
                .subjects(subjects.stream().filter(s -> s.getName().equals("MAPEH")).collect(Collectors.toList()))
                .build();

        Teacher mt = Teacher.builder()
                .firstName("Mother Tongue")
                .middleName("ABC")
                .lastName("Teacher")
                .school(school)
                .subjects(subjects.stream().filter(s -> s.getName().equals("Mother Tongue")).collect(Collectors.toList()))
                .build();

        Teacher ap = Teacher.builder()
                .firstName("AP")
                .middleName("ABC")
                .lastName("Teacher")
                .school(school)
                .subjects(subjects.stream().filter(s -> s.getName().equals("Araling Panlipunan")).collect(Collectors.toList()))
                .build();

        teacherRepository.saveAll(Arrays.asList(english, science, mapeh, math, fil, esp, mt, ap));
    }

    private void seedSections() {
        if (!sectionRepository.findAll().isEmpty()) {
            return;
        }
        School school = schoolRepository.findById(Long.valueOf(Long.valueOf(122474))).get();
        List<Subject> subjects = subjectRepository.findAll();

        Section gradeOneSection1 = Section.builder()
                .grade(1)
                .name("1")
                .school(school)
                .adviser(teacherRepository.findById(Long.valueOf(1)).get())
                .subjects(subjects)
                .school(school)
                .build();

        Section gradeTwoSection1 = Section.builder()
                .grade(2)
                .name("1")
                .school(school)
                .adviser(teacherRepository.findById(Long.valueOf(2)).get())
                .subjects(subjects)
                .school(school)
                .build();

        Section gradeThreeSection1 = Section.builder()
                .grade(3)
                .name("1")
                .school(school)
                .adviser(teacherRepository.findById(Long.valueOf(3)).get())
                .subjects(subjects)
                .school(school)
                .build();

        sectionRepository.saveAll(Arrays.asList(gradeOneSection1, gradeTwoSection1, gradeThreeSection1));
    }

    public void seedTasks() {
        if (!taskRepository.findAll().isEmpty()) {
            return;
        }

        List<Subject> subjects = subjectRepository.findAll();
        List<Teacher> teachers = teacherRepository.findAll();
        List<Section> sections = sectionRepository.findAll();

        subjects.stream().forEachOrdered(s -> System.out.println(s.getName()));
        teachers.stream().forEachOrdered(t -> System.out.println(t.getFirstName()));
        sections.stream().forEachOrdered(s -> System.out.println(s.getGrade() + " " + s.getName()));

        Subject sub = subjects.stream().filter(s -> s.getName().equals("English")).findFirst().get();
        System.out.println(sub.getName());

        Teacher tec = teachers.stream().filter(t -> t.getId().equals(Long.valueOf(1))).findFirst().get();
        System.out.println(tec.getFirstName());

        Section sec = sections.stream().filter(s -> s.getId().equals(Long.valueOf(1))).findFirst().get();
        System.out.println(sec.getName());

        Task englishWritten = Task.builder()
                .subject(subjects.stream().filter(s -> s.getName().equals("English")).findFirst().get())
                .name("Chapter 1: Basic Grammar")
                .quarter(1)
                .type(TaskType.WRITTEN)
                .teacher(teachers.stream().filter(t -> t.getId().equals(Long.valueOf(1))).findFirst().get())
                .section(sections.stream().filter(s -> s.getId().equals(Long.valueOf(1))).findFirst().get())
                .build();

        taskRepository.save(englishWritten);
    }
}
