package com.group11.schoolmanagementsystem.department;

import com.group11.schoolmanagementsystem.department.converter.DepartmentDtoConverter;
import com.group11.schoolmanagementsystem.department.dto.DepartmentDto;
import com.group11.schoolmanagementsystem.exception.ApiRequestException;
import com.group11.schoolmanagementsystem.teacher.Teacher;
import com.group11.schoolmanagementsystem.teacher.TeacherRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private DepartmentRepository departmentRepository;
    private DepartmentDtoConverter departmentDtoConverter;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository, DepartmentDtoConverter departmentDtoConverter) {
        this.departmentRepository = departmentRepository;
        this.departmentDtoConverter = departmentDtoConverter;
    }

    public DepartmentDto create(String name) {
        Department department = Department.builder()
                .name(name)
                .build();
        Department createdDepartment = departmentRepository.save(department);

        return departmentDtoConverter.departmentToDto(createdDepartment);
    }

    public List<DepartmentDto> getAll() {
        List<Department> departments = departmentRepository.findAll();

        return departments.stream().map(d -> departmentDtoConverter.departmentToDto(d)).collect(Collectors.toList());
    }

    public DepartmentDto update(Department department) {
        if (departmentRepository.findById(department.getId()).isEmpty()) {
            throw new ApiRequestException("Department Doesn't Exist");
        }

        Department departmentUpdate = Department.builder()
                .id(department.getId())
                .name(department.getName())
                .build();

        Department updatedDepartment = departmentRepository.save(departmentUpdate);

        return departmentDtoConverter.departmentToDto(updatedDepartment);
    }

    public DepartmentDto delete(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isEmpty()) {
            throw new ApiRequestException("Department Doesn't exist");
        }

        List<Teacher> teachers = department.get().getTeachers();

        for (Teacher t : teachers) {
            t.setDepartment(null);
        }

        departmentRepository.deleteById(id);

        return departmentDtoConverter.departmentToDto(department.get());
    }
}
