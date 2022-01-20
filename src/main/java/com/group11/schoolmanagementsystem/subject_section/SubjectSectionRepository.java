package com.group11.schoolmanagementsystem.subject_section;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubjectSectionRepository extends JpaRepository<SubjectSection, SubjectSectionKey> {
    Optional<List<SubjectSection>> findSubjectSectionsByTeacher_Id(Long teacherId);
    Optional<List<SubjectSection>> findSubjectSectionsBySection_Id(Long sectionId);
    Optional<SubjectSection> findSubjectSectionBySection_IdAndSubject_Id(Long sectionId, Long subjectId);
}
