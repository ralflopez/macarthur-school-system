package com.group11.schoolmanagementsystem.subject;

import com.group11.schoolmanagementsystem.student.Student;
import com.group11.schoolmanagementsystem.student.StudentService;
import com.group11.schoolmanagementsystem.subject.dto.AssignSubjectDto;
import com.group11.schoolmanagementsystem.subject.dto.CreateSubjectDto;
import com.group11.schoolmanagementsystem.subject.dto.EditSubjectDto;
import com.group11.schoolmanagementsystem.subject.dto.SubjectDto;
import com.group11.schoolmanagementsystem.subject_section.dto.SubjectSectionDto;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("subject")
@CrossOrigin
@Api(tags = "Subject")
public class SubjectController {
    private SubjectService subjectService;
    private StudentService studentService;

    @Autowired
    public SubjectController(SubjectService subjectService, StudentService studentService) {
        this.subjectService = subjectService;
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public ResponseEntity<SubjectDto> createSubject(@Valid @RequestBody CreateSubjectDto createSubjectDto) {
        SubjectDto subject = subjectService.create(createSubjectDto);

        return new ResponseEntity(subject, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SubjectDto>> getSubjects() {
        List<SubjectDto> subjects = subjectService.getAll();

        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SubjectDto> updateSubject(@RequestBody EditSubjectDto editSubjectDto, @PathVariable("id") Long id) {
        SubjectDto subject = subjectService.update(id, editSubjectDto);

        return new ResponseEntity<>(subject, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SubjectDto> deleteSubject(@PathVariable("id") Long id) {
        SubjectDto subject = subjectService.delete(id);

        return new ResponseEntity<>(subject, HttpStatus.OK);
    }

    @PostMapping("/assign")
    public ResponseEntity<AssignSubjectDto> assignTeacherToSubject(@RequestBody AssignSubjectDto assignSubjectDto) {
        AssignSubjectDto assignSubject = subjectService.assignTeacherToSubject(assignSubjectDto);

        return new ResponseEntity(assignSubject, HttpStatus.OK);
    }
}
