package com.group11.schoolmanagementsystem.teacher;

import com.group11.schoolmanagementsystem.enums.SubjectDepartment;
import com.group11.schoolmanagementsystem.subject.dto.SubjectDto;
import com.group11.schoolmanagementsystem.teacher.dto.CreateTeacherDto;
import com.group11.schoolmanagementsystem.teacher.dto.DeleteTeacherDto;
import com.group11.schoolmanagementsystem.teacher.dto.TeacherDto;
import com.group11.schoolmanagementsystem.teacher.dto.UpdateTeacherDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    private TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService, ModelMapper modelMapper) {
        this.teacherService = teacherService;
    }

    @PostMapping("/create")
    public ResponseEntity<TeacherDto> createTeacher(@Valid @RequestBody CreateTeacherDto createTeacherDto) {
        TeacherDto teacher = teacherService.create(createTeacherDto);

        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TeacherDto>> getAllTeachers() {
        List<TeacherDto> teachers = teacherService.findAll();

        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> getById(@PathVariable("id") Long id) {
        TeacherDto teacherDto = teacherService.findById(id);
        return new ResponseEntity<>(teacherDto, HttpStatus.OK);
    }

    @GetMapping("/department")
    public ResponseEntity<List<TeacherDto>> getAllByDepartmentId(@RequestParam("departmentId") Long id) {
        List<TeacherDto> teachers = teacherService.findByDepartmentId(id);

        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<TeacherDto> updateTeacher(@RequestBody UpdateTeacherDto updateTeacherDto) {
        TeacherDto teacher = teacherService.update(updateTeacherDto);

        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<TeacherDto> deleteTeacherById(@RequestBody DeleteTeacherDto deleteTeacherDto) {
        TeacherDto teacher = teacherService.delete(deleteTeacherDto.getId());

        return new ResponseEntity(teacher, HttpStatus.OK);
    }

}
