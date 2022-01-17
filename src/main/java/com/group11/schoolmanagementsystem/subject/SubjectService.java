package com.group11.schoolmanagementsystem.subject;

import com.group11.schoolmanagementsystem.exception.ApiRequestException;
import com.group11.schoolmanagementsystem.section.Section;
import com.group11.schoolmanagementsystem.section.SectionRepository;
import com.group11.schoolmanagementsystem.section.converter.SectionDtoConverter;
import com.group11.schoolmanagementsystem.section.dto.SectionDto;
import com.group11.schoolmanagementsystem.student.Student;
import com.group11.schoolmanagementsystem.subject.converter.SubjectDtoConverter;
import com.group11.schoolmanagementsystem.subject.dto.AssignSubjectDto;
import com.group11.schoolmanagementsystem.subject.dto.CreateSubjectDto;
import com.group11.schoolmanagementsystem.subject.dto.DeleteSubjectDto;
import com.group11.schoolmanagementsystem.subject.dto.SubjectDto;
import com.group11.schoolmanagementsystem.subject_section.SubjectSection;
import com.group11.schoolmanagementsystem.subject_section.SubjectSectionKey;
import com.group11.schoolmanagementsystem.subject_section.SubjectSectionRepository;
import com.group11.schoolmanagementsystem.subject_section.dto.SubjectSectionDto;
import com.group11.schoolmanagementsystem.teacher.Teacher;
import com.group11.schoolmanagementsystem.teacher.TeacherRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubjectService {
    private SubjectRepository subjectRepository;
    private TeacherRepository teacherRepository;
    private SectionRepository sectionRepository;
    private SubjectDtoConverter subjectDtoConverter;
    private SectionDtoConverter sectionDtoConverter;
    private SubjectSectionRepository subjectSectionRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository, TeacherRepository teacherRepository, SectionRepository sectionRepository, SubjectDtoConverter subjectDtoConverter, SubjectSectionRepository subjectSectionRepository, SectionDtoConverter sectionDtoConverter) {
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
        this.sectionRepository = sectionRepository;
        this.subjectDtoConverter = subjectDtoConverter;
        this.subjectSectionRepository = subjectSectionRepository;
        this.sectionDtoConverter = sectionDtoConverter;
    }

    public SubjectDto create(CreateSubjectDto createSubjectDto) {
        Subject subject = Subject.builder()
                .name(createSubjectDto.getName())
                .grade(createSubjectDto.getGrade())
                .build();

        Subject createdSubject = subjectRepository.save(subject);

        return subjectDtoConverter.subjectToDto(createdSubject);
    }

    public List<SubjectDto> getAll() {
        List<Subject> subjects = subjectRepository.findAll();

        return subjects.stream().map(s -> subjectDtoConverter.subjectToDto(s)).collect(Collectors.toList());
    }

    public SubjectDto update(SubjectDto subjectDto) {
        Optional<Subject> subject = subjectRepository.findById(subjectDto.getId());
        if (subject.isEmpty()) {
            throw new ApiRequestException("Subject Not Found");
        }

        subject.get().setGrade(subjectDto.getGrade());
        subject.get().setName(subjectDto.getName());

        Subject updatedSubject = subjectRepository.save(subject.get());

        return subjectDtoConverter.subjectToDto(updatedSubject);
    }

    public SubjectDto delete(Long id) {
        Optional<Subject> subject = subjectRepository.findById(id);
        if (subject.isEmpty()) {
            throw new ApiRequestException("Subject Doesn't Exist");
        }

        subjectRepository.deleteById(id);

        return subjectDtoConverter.subjectToDto(subject.get());
    }

    public AssignSubjectDto assignTeacherToSubject(AssignSubjectDto assignSubjectDto) {
        Optional<Teacher> teacher = teacherRepository.findById(assignSubjectDto.getTeacherId());
        if (teacher.isEmpty()) {
            throw new ApiRequestException("Teacher Not Found");
        }

        Optional<Subject> subject = subjectRepository.findById(assignSubjectDto.getSubjectId());
        if (subject.isEmpty()) {
            throw new ApiRequestException("Subject Not Found");
        }

        Optional<Section> section = sectionRepository.findById(assignSubjectDto.getSectionId());
        if (section.isEmpty()) {
            throw new ApiRequestException("Section Not Found");
        }

        SubjectSection subjectSection = SubjectSection.builder()
                .subjectSectionKey(new SubjectSectionKey(subject.get().getId(), section.get().getId()))
                .section(section.get())
                .subject(subject.get())
                .teacher(teacher.get())
                .build();

        subjectSectionRepository.save(subjectSection);

        return assignSubjectDto;
    }

    public List<SubjectSectionDto> getTeachersSubjects(Long teacherId) {
        List<SubjectSectionDto> results = new ArrayList<>();
        Optional<List<SubjectSection>> subjectSections = subjectSectionRepository.findSubjectSectionsByTeacher_Id(teacherId);
        if (subjectSections.isEmpty()) {
            throw new ApiRequestException("Teachers Subject Not Found");
        }

        for (SubjectSection s : subjectSections.get()) {
            Subject subject = s.getSubject();
            Section section = s.getSection();
            SubjectDto subjectDto = subjectDtoConverter.subjectToDto(subject);
            SectionDto sectionDto = sectionDtoConverter.sectionToDto(section);

            SubjectSectionDto subjectSectionDto = SubjectSectionDto.builder()
                    .section(sectionDto)
                    .subject(subjectDto)
                    .build();

            results.add(subjectSectionDto);
        }

        return results;
    }
}
