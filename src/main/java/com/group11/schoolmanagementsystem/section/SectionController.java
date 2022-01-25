package com.group11.schoolmanagementsystem.section;

import com.group11.schoolmanagementsystem.section.dto.CreateSectionDto;
import com.group11.schoolmanagementsystem.section.dto.SectionDto;
import com.group11.schoolmanagementsystem.section.dto.UpdateSectionDto;
import com.group11.schoolmanagementsystem.student.StudentService;
import com.group11.schoolmanagementsystem.student.dto.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/section")
@CrossOrigin
public class SectionController {
    private SectionService sectionService;
    private StudentService studentService;

    @Autowired
    public SectionController(SectionService sectionService, StudentService studentService) {
        this.sectionService = sectionService;
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public ResponseEntity<SectionDto> createSection(@RequestBody CreateSectionDto createSectionDto) {
        SectionDto sectionDto = sectionService.create(createSectionDto);

        return new ResponseEntity<>(sectionDto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SectionDto>> getAllSections() {
        List<SectionDto> sections = sectionService.getAll();

        return new ResponseEntity<>(sections, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SectionDto> updateSection(@RequestBody UpdateSectionDto updateSectionDto, @PathVariable("id") Long id) {
        SectionDto section = sectionService.update(id, updateSectionDto);

        return new ResponseEntity<>(section, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SectionDto> deleteSection(@PathVariable("id") Long id) {
        SectionDto section = sectionService.delete(id);

        return new ResponseEntity(section, HttpStatus.OK);
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<List<StudentDto>> getStudentsBySection(@PathVariable("id") Long id) {
        List<StudentDto> studentDtos = studentService.findBySectionId(id);
        return new ResponseEntity<>(studentDtos, HttpStatus.OK);
    }

}
