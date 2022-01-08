package com.group11.schoolmanagementsystem.school;

import com.group11.schoolmanagementsystem.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolService {
    private SchoolRepository schoolRepository;

    @Autowired
    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public School addSchool(School school) {

        if (schoolRepository.findById(school.getId()).isPresent()) {
            throw new ApiRequestException("School already exists");
        }

        return schoolRepository.save(school);
    }
}
