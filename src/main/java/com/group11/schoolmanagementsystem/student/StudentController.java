package com.group11.schoolmanagementsystem.student;

import com.group11.schoolmanagementsystem.section.dto.SectionDto;
import com.group11.schoolmanagementsystem.student.dto.CreateStudentDto;
import com.group11.schoolmanagementsystem.student.dto.DeleteStudentDto;
import com.group11.schoolmanagementsystem.student.dto.StudentDto;
import com.group11.schoolmanagementsystem.student.dto.UpdateStudentDto;
import com.group11.schoolmanagementsystem.subject.dto.SubjectDto;
import com.group11.schoolmanagementsystem.subject_section.dto.SubjectSectionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("student")
public class StudentController {
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createStudent(@RequestBody CreateStudentDto createStudentDto) {
        StudentDto studentDto = studentService.create(createStudentDto);
        return new ResponseEntity(studentDto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<StudentDto>> getAllBySection() {
        List<StudentDto> studentDtos = studentService.findAll();
        return new ResponseEntity<>(studentDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable("id") Long id) {
        StudentDto studentDto = studentService.findById(id);
        return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }

    @GetMapping("/section")
    public ResponseEntity<List<StudentDto>> getStudentsBySection(@RequestParam("sectionId") Long id) {
        List<StudentDto> studentDtos = studentService.findBySectionId(id);
        return new ResponseEntity<>(studentDtos, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<StudentDto> updateStudent(@RequestBody UpdateStudentDto updateStudentDto) {
        StudentDto studentDto = studentService.update(updateStudentDto);
        return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<StudentDto> deleteStudent(@RequestBody DeleteStudentDto deleteStudentDto) {
        StudentDto studentDto = studentService.delete(deleteStudentDto.getId());
        return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }

    @GetMapping("/subjects")
    public ResponseEntity<List<SubjectDto>> getStudentsSubjects(@RequestParam("studentId") Long studentId) {
        List<SubjectDto> studentDtos = studentService.getSubjects(studentId);
        return new ResponseEntity<>(studentDtos, HttpStatus.OK);
    }

}
