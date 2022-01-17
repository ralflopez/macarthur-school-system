package com.group11.schoolmanagementsystem.subject;

import com.group11.schoolmanagementsystem.student.dto.CreateStudentDto;
import com.group11.schoolmanagementsystem.subject.dto.AssignSubjectDto;
import com.group11.schoolmanagementsystem.subject.dto.CreateSubjectDto;
import com.group11.schoolmanagementsystem.subject.dto.DeleteSubjectDto;
import com.group11.schoolmanagementsystem.subject.dto.SubjectDto;
import com.group11.schoolmanagementsystem.subject_section.dto.SubjectSectionDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.Assign;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("subject")
public class SubjectController {
    private SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService, ModelMapper modelMapper) {
        this.subjectService = subjectService;
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

    @PutMapping("/edit")
    public ResponseEntity<SubjectDto> editSubject(@Valid @RequestBody SubjectDto subjectDto) {
        SubjectDto subject = subjectService.update(subjectDto);

        return new ResponseEntity<>(subject, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<SubjectDto> deleteSubject(@Valid @RequestBody DeleteSubjectDto deleteSubjectDto) {
        SubjectDto subject = subjectService.delete(deleteSubjectDto.getId());

        return new ResponseEntity<>(subject, HttpStatus.OK);
    }

    @PostMapping("/assign")
    public ResponseEntity<AssignSubjectDto> assignTeacherToSubject(@RequestBody AssignSubjectDto assignSubjectDto) {
        AssignSubjectDto assignSubject = subjectService.assignTeacherToSubject(assignSubjectDto);

        return new ResponseEntity(assignSubject, HttpStatus.OK);
    }

    @GetMapping("/my")
    public ResponseEntity<List<SubjectSectionDto>> getTeachersSubjects(@RequestParam("teacherId") Long teacherId) {
        List<SubjectSectionDto> subjectSectionDtos = subjectService.getTeachersSubjects(teacherId);
        return new ResponseEntity<>(subjectSectionDtos, HttpStatus.OK);
    }

}
