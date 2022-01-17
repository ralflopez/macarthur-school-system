package com.group11.schoolmanagementsystem.subject_section;

import com.group11.schoolmanagementsystem.exception.ApiRequestException;
import com.group11.schoolmanagementsystem.section.SectionRepository;
import com.group11.schoolmanagementsystem.section.converter.SectionDtoConverter;
import com.group11.schoolmanagementsystem.subject.SubjectRepository;
import com.group11.schoolmanagementsystem.subject.SubjectService;
import com.group11.schoolmanagementsystem.subject.converter.SubjectDtoConverter;
import com.group11.schoolmanagementsystem.teacher.TeacherRepository;
import com.group11.schoolmanagementsystem.teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectSectionService {
    private SubjectSectionRepository subjectSectionRepository;
    private TeacherService teacherService;
    private SubjectDtoConverter subjectDtoConverter;
    private SectionDtoConverter sectionDtoConverter;

    @Autowired
    public SubjectSectionService(SubjectSectionRepository subjectSectionRepository, TeacherService teacherService, SubjectDtoConverter subjectDtoConverter, SectionDtoConverter sectionDtoConverter) {
        this.subjectSectionRepository = subjectSectionRepository;
        this.teacherService = teacherService;
        this.subjectDtoConverter = subjectDtoConverter;
        this.sectionDtoConverter = sectionDtoConverter;
    }

    public List<SubjectSection> getSubjectSectionsBySectionId(Long sectionId) {
        Optional<List<SubjectSection>> subjectSection = subjectSectionRepository.findSubjectSectionsBySection_Id(sectionId);
        if (subjectSection.isEmpty()) {
            throw new ApiRequestException("Section / Section Not Found");
        }

        return subjectSection.get();
    }
}
