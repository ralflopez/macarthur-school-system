package com.group11.schoolmanagementsystem.department;

import com.group11.schoolmanagementsystem.department.dto.DeleteDepartmentDto;
import com.group11.schoolmanagementsystem.department.dto.DepartmentDto;
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

    @PutMapping("/edit")
    ResponseEntity<DepartmentDto> editDepartment(@Valid @RequestBody Department department) {
        DepartmentDto departmentObj = departmentService.update(department);

        return new ResponseEntity<>(departmentObj,HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    ResponseEntity<DepartmentDto> deleteDepartment(@Valid @RequestBody DeleteDepartmentDto deleteDepartmentDto) {
        DepartmentDto department = departmentService.delete(deleteDepartmentDto.getId());

        return new ResponseEntity<>(department, HttpStatus.OK);
    }
}
