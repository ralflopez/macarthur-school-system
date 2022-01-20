package com.group11.schoolmanagementsystem.section;

import com.group11.schoolmanagementsystem.exception.ApiRequestException;
import com.group11.schoolmanagementsystem.section.dto.CreateSectionDto;
import com.group11.schoolmanagementsystem.section.dto.SectionDto;
import com.group11.schoolmanagementsystem.section.converter.SectionDtoConverter;
import com.group11.schoolmanagementsystem.section.dto.UpdateSectionDto;
import com.group11.schoolmanagementsystem.student.Student;
import com.group11.schoolmanagementsystem.subject_section.SubjectSection;
import com.group11.schoolmanagementsystem.subject_section.SubjectSectionRepository;
import com.group11.schoolmanagementsystem.task.Task;
import com.group11.schoolmanagementsystem.task.TaskRepository;
import com.group11.schoolmanagementsystem.teacher.Teacher;
import com.group11.schoolmanagementsystem.teacher.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SectionService {
    private SectionRepository sectionRepository;
    private TeacherRepository teacherRepository;
    private SectionDtoConverter sectionDtoConverter;
    private SubjectSectionRepository subjectSectionRepository;
    private TaskRepository taskRepository;

    @Autowired
    public SectionService(SectionRepository sectionRepository, TeacherRepository teacherRepository, SectionDtoConverter sectionDtoConverter, SubjectSectionRepository subjectSectionRepository, TaskRepository taskRepository) {
        this.sectionRepository = sectionRepository;
        this.teacherRepository = teacherRepository;
        this.sectionDtoConverter = sectionDtoConverter;
        this.subjectSectionRepository = subjectSectionRepository;
        this.taskRepository = taskRepository;
    }

    public SectionDto create(CreateSectionDto createSectionDto) {
        Optional<Teacher> adviser = teacherRepository.findById(createSectionDto.getAdviserId());
        if (adviser.isEmpty()) {
            throw new ApiRequestException("Adviser Not Found");
        }

        Section section = Section.builder()
                .grade(createSectionDto.getGrade())
                .name(createSectionDto.getName())
                .adviser(adviser.get())
                .build();

        Section createdSection = sectionRepository.save(section);

        return sectionDtoConverter.sectionToDto(createdSection);
    }

    public List<SectionDto> getAll() {
        List<Section> sections = sectionRepository.findAll();

        return sections.stream().map(s -> {
            SectionDto sectionDto = sectionDtoConverter.sectionToDto(s);
            return sectionDto;
        }).collect(Collectors.toList());
    }

    public SectionDto delete(Long id) {
        Optional<Section> section = sectionRepository.findById(id);
        if (section.isEmpty()) {
            throw new ApiRequestException("Section Not Found");
        }

        for (Student student : section.get().getStudents()) {
            student.setSection(null);
        }

        sectionRepository.deleteById(id);

        return sectionDtoConverter.sectionToDto(section.get());
    }

    public SectionDto update(Long id, UpdateSectionDto updateSectionDto) {
        Optional<Teacher> adviser = teacherRepository.findById(updateSectionDto.getAdviserId());
        if (adviser.isEmpty()) {
            throw new ApiRequestException("Adviser Not Found");
        }

        Optional<Section> section = sectionRepository.findById(id);
        if (section.isEmpty()) {
            throw new ApiRequestException("Section Not Found");
        }

        section.get().setAdviser(adviser.get());
        section.get().setGrade(updateSectionDto.getGrade());
        section.get().setName(updateSectionDto.getName());

        Section updatedSection = sectionRepository.save(section.get());

        return sectionDtoConverter.sectionToDto(updatedSection);
    }

    public SectionDto getAdvisoryClass(Long adviserId) {
        Optional<Section> section = sectionRepository.findByAdviser_Id(adviserId);
        if (section.isEmpty()) {
            throw new ApiRequestException("Advisor Class Not Found");
        }

        return sectionDtoConverter.sectionToDto(section.get());
    }
}
