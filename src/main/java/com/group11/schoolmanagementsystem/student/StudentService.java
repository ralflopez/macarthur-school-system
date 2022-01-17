package com.group11.schoolmanagementsystem.student;

import com.group11.schoolmanagementsystem.exception.ApiRequestException;
import com.group11.schoolmanagementsystem.section.Section;
import com.group11.schoolmanagementsystem.section.SectionRepository;
import com.group11.schoolmanagementsystem.student.converter.StudentDtoConverter;
import com.group11.schoolmanagementsystem.student.dto.CreateStudentDto;
import com.group11.schoolmanagementsystem.student.dto.StudentDto;
import com.group11.schoolmanagementsystem.student.dto.UpdateStudentDto;
import com.group11.schoolmanagementsystem.subject.converter.SubjectDtoConverter;
import com.group11.schoolmanagementsystem.subject.dto.SubjectDto;
import com.group11.schoolmanagementsystem.subject_section.SubjectSection;
import com.group11.schoolmanagementsystem.subject_section.SubjectSectionRepository;
import com.group11.schoolmanagementsystem.subject_section.dto.SubjectSectionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private StudentRepository studentRepository;
    private SectionRepository sectionRepository;
    private StudentDtoConverter studentDtoConverter;
    private SubjectSectionRepository subjectSectionRepository;
    private SubjectDtoConverter subjectDtoConverter;

    @Autowired
    public StudentService(StudentRepository studentRepository, SectionRepository sectionRepository, StudentDtoConverter studentDtoConverter, SubjectSectionRepository subjectSectionRepository, SubjectDtoConverter subjectDtoConverter) {
        this.studentRepository = studentRepository;
        this.sectionRepository = sectionRepository;
        this.studentDtoConverter = studentDtoConverter;
        this.subjectSectionRepository = subjectSectionRepository;
        this.subjectDtoConverter = subjectDtoConverter;
    }

    public StudentDto create(CreateStudentDto createStudentDto) {
        Optional<Student> student = studentRepository.findById(createStudentDto.getLrn());
        if (student.isPresent()) {
            throw new ApiRequestException("Student Already Exists");
        }
        Optional<Section> studentSection = sectionRepository.findById(createStudentDto.getSectionId());
        if (studentSection.isEmpty()) {
            throw new ApiRequestException("Section Not Found");
        }

        Student newStudent = Student.builder()
                .birthDay(createStudentDto.getBirthDay())
                .birthMonth(createStudentDto.getBirthMonth())
                .birthYear(createStudentDto.getBirthYear())
                .firstName(createStudentDto.getFirstName())
                .middleName(createStudentDto.getMiddleName())
                .lastName(createStudentDto.getLastName())
                .gender(createStudentDto.getGender())
                .lrn(createStudentDto.getLrn())
                .username(createStudentDto.getUsername())
                .password(createStudentDto.getPassword())
                .section(studentSection.get())
                .build();

        Student createdStudent = studentRepository.save(newStudent);

        return studentDtoConverter.studentToDto(createdStudent);
    }

    public List<StudentDto> findAll() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(s -> studentDtoConverter.studentToDto(s)).collect(Collectors.toList());
    }

    public StudentDto findById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty()) {
            throw new ApiRequestException("Student Not Found");
        }

        return studentDtoConverter.studentToDto(student.get());
    }

    public StudentDto update(UpdateStudentDto updateStudentDto) {
        Optional<Student> student = studentRepository.findById(updateStudentDto.getLrn());
        if (student.isEmpty()) {
            throw new ApiRequestException("Student Not Found");
        }

        Optional<Section> section = sectionRepository.findById(updateStudentDto.getSectionId());
        if (section.isEmpty()) {
            throw new ApiRequestException("Section Not Found");
        }

        Student studentUpdate = student.get();
        studentUpdate.setSection(section.get());
        studentUpdate.setBirthDay(updateStudentDto.getBirthDay());
        studentUpdate.setBirthMonth(updateStudentDto.getBirthMonth());
        studentUpdate.setBirthYear(updateStudentDto.getBirthYear());
        studentUpdate.setGender(updateStudentDto.getGender());
        studentUpdate.setFirstName(updateStudentDto.getFirstName());
        studentUpdate.setMiddleName(updateStudentDto.getMiddleName());
        studentUpdate.setLastName(updateStudentDto.getLastName());
        studentUpdate.setUsername(updateStudentDto.getUsername());
        studentUpdate.setPassword(updateStudentDto.getPassword());
        studentUpdate.setLrn(updateStudentDto.getLrn());

        Student updatedStudent = studentRepository.save(studentUpdate);

        return studentDtoConverter.studentToDto(updatedStudent);
    }

    public List<StudentDto> findBySectionId(Long id) {
        List<Student> students = studentRepository.findStudentsBySection_Id(id);
        return students.stream().map(s -> studentDtoConverter.studentToDto(s)).collect(Collectors.toList());
    }

    public StudentDto delete(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if(student.isEmpty()) {
            throw new ApiRequestException("Student Not Found");
        }

        studentRepository.deleteById(id);

        return studentDtoConverter.studentToDto(student.get());
    }

    public List<SubjectDto> getSubjects(Long studentId) {
        List<SubjectDto> subjectDtos = new ArrayList<>();
        Optional<Student> student = studentRepository.findById(studentId);
        Optional<List<SubjectSection>> subjectSections = subjectSectionRepository.findSubjectSectionsBySection_Id(student.get().getSection().getId());
        for (SubjectSection s : subjectSections.get()) {
            subjectDtos.add(subjectDtoConverter.subjectToDto(s.getSubject()));
        }

        return subjectDtos;
    }
}
