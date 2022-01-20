package com.group11.schoolmanagementsystem.teacher;

import com.group11.schoolmanagementsystem.department.Department;
import com.group11.schoolmanagementsystem.department.DepartmentRepository;
import com.group11.schoolmanagementsystem.exception.ApiRequestException;
import com.group11.schoolmanagementsystem.section.Section;
import com.group11.schoolmanagementsystem.section.SectionRepository;
import com.group11.schoolmanagementsystem.section.dto.SectionDto;
import com.group11.schoolmanagementsystem.subject.dto.SubjectDto;
import com.group11.schoolmanagementsystem.teacher.converter.TeacherDtoConverter;
import com.group11.schoolmanagementsystem.teacher.dto.CreateTeacherDto;
import com.group11.schoolmanagementsystem.teacher.dto.TeacherDto;
import com.group11.schoolmanagementsystem.teacher.dto.UpdateTeacherDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherService {
    private TeacherRepository teacherRepository;
    private DepartmentRepository departmentRepository;
    private TeacherDtoConverter teacherDtoConverter;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository, DepartmentRepository departmentRepository, TeacherDtoConverter teacherDtoConverter) {
        this.teacherRepository = teacherRepository;
        this.departmentRepository = departmentRepository;
        this.teacherDtoConverter = teacherDtoConverter;
    }

    public TeacherDto create(CreateTeacherDto createTeacherDto) {
        Optional<Teacher> teacherUsername = teacherRepository.findTeacherByUsername(createTeacherDto.getUsername());
        if (teacherUsername.isPresent()) {
            throw new ApiRequestException("Username Already Taken");
        }

        Optional<Department> department = departmentRepository.findById(createTeacherDto.getDepartmentId());
        if (department.isEmpty()) {
            throw new ApiRequestException("Department Doesn't Exist");
        }

        Teacher teacher = Teacher.builder()
                .username(createTeacherDto.getUsername())
                .password(createTeacherDto.getPassword())
                .firstName(createTeacherDto.getFirstName())
                .middleName(createTeacherDto.getMiddleName())
                .lastName(createTeacherDto.getLastName())
                .department(department.get())
                .build();

        Teacher savedTeacher = teacherRepository.save(teacher);

        return teacherDtoConverter.teacherToDto(savedTeacher);
    }

    public List<TeacherDto> findAll() {
        List<Teacher> teachers = teacherRepository.findAll();
        List<TeacherDto> teacherDtos = new ArrayList<>();

        for (Teacher teacher : teachers) {
            TeacherDto teacherDto = teacherDtoConverter.teacherToDto(teacher);
            teacherDtos.add(teacherDto);
        }

        return teacherDtos;
    }

    public TeacherDto findById(Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);

        if (teacher.isEmpty()) {
            throw new ApiRequestException("Teacher Doesn't Exist");
        }

        return teacherDtoConverter.teacherToDto(teacher.get());
    }

    public List<TeacherDto> findByDepartmentId(Long departmentId) {
        Optional<List<Teacher>> teachers = teacherRepository.findByAllByDepartmentId(departmentId);

        if (teachers.isEmpty()) {
            throw new ApiRequestException("Unable to query teachers");
        }

        return teachers.get().stream().map(t -> teacherDtoConverter.teacherToDto(t)).collect(Collectors.toList());
    }

    public TeacherDto update(Long id, UpdateTeacherDto updateTeacherDto) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (teacher.isEmpty()) {
            throw new ApiRequestException("Teacher Not Found");
        }

        Optional<Department> department = departmentRepository.findById(updateTeacherDto.getDepartmentId());
        if (department.isEmpty()) {
            throw new ApiRequestException("Department Not Found");
        }

        Teacher teacherUpdate = teacher.get();
        teacherUpdate.setDepartment(department.get());
        teacherUpdate.setUsername(updateTeacherDto.getUsername());
        teacherUpdate.setPassword(updateTeacherDto.getPassword());
        teacherUpdate.setFirstName(updateTeacherDto.getFirstName());
        teacherUpdate.setMiddleName(updateTeacherDto.getMiddleName());
        teacherUpdate.setLastName(updateTeacherDto.getLastName());

        Teacher updatedTeacher = teacherRepository.save(teacherUpdate);

        return teacherDtoConverter.teacherToDto(updatedTeacher);
    }

    public TeacherDto delete(Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (teacher.isEmpty()) {
            throw new ApiRequestException("Teacher Doesn't Exist");
        }

        Optional<Department> department = departmentRepository.findByHead_Id(id);
        if (department.isPresent()) {
            throw new ApiRequestException("Teacher is head of a department");
        }

        teacherRepository.deleteById(id);

        return teacherDtoConverter.teacherToDto(teacher.get());
    }
}
