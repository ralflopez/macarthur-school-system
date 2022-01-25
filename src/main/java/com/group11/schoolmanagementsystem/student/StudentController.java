package com.group11.schoolmanagementsystem.student;

import com.group11.schoolmanagementsystem.student.dto.CreateStudentDto;
import com.group11.schoolmanagementsystem.student.dto.StudentDto;
import com.group11.schoolmanagementsystem.student.dto.UpdateStudentDto;
import com.group11.schoolmanagementsystem.subject.dto.SubjectDto;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
@CrossOrigin
@Api(tags = "Student")
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

    @PutMapping("/update/{id}")
    public ResponseEntity<StudentDto> updateStudent(@RequestBody UpdateStudentDto updateStudentDto, @PathVariable("id") Long id) {
        StudentDto studentDto = studentService.update(id, updateStudentDto);
        return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<StudentDto> deleteStudent(@PathVariable("id") Long id) {
        StudentDto studentDto = studentService.delete(id);
        return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }

    @GetMapping("/subjects/{id}")
    public ResponseEntity<List<SubjectDto>> getStudentsSubjects(@PathVariable("id") Long id) {
        List<SubjectDto> studentDtos = studentService.getSubjects(id);
        return new ResponseEntity<>(studentDtos, HttpStatus.OK);
    }

}
