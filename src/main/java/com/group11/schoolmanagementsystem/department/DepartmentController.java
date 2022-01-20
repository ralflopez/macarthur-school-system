package com.group11.schoolmanagementsystem.department;

import com.group11.schoolmanagementsystem.department.dto.DepartmentDto;
import com.group11.schoolmanagementsystem.department.dto.EditDepartmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("department")
public class DepartmentController {
    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/create")
    ResponseEntity<DepartmentDto> createDepartment(@Valid @RequestBody Department department) {
        DepartmentDto departmentObj = departmentService.create(department.getName());
        return new ResponseEntity(departmentObj, HttpStatus.OK);
    }

    @GetMapping("/all")
    ResponseEntity<List<DepartmentDto>> getAllDepartments() {
        List<DepartmentDto> departments = departmentService.getAll();

        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    ResponseEntity<DepartmentDto> editDepartment(@RequestBody EditDepartmentDto editDepartmentDto, @PathVariable("id") Long id) {
        DepartmentDto departmentObj = departmentService.update(id, editDepartmentDto);

        return new ResponseEntity<>(departmentObj,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<DepartmentDto> deleteDepartment(@PathVariable("id") Long id) {
        DepartmentDto department = departmentService.delete(id);

        return new ResponseEntity<>(department, HttpStatus.OK);
    }
}
