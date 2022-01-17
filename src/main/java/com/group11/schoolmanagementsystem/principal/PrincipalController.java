package com.group11.schoolmanagementsystem.principal;

import com.group11.schoolmanagementsystem.teacher.Teacher;
import com.group11.schoolmanagementsystem.teacher.TeacherService;
import com.group11.schoolmanagementsystem.teacher.dto.CreateTeacherDto;
import com.group11.schoolmanagementsystem.teacher.dto.TeacherDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/principal")
public class PrincipalController {
    private PrincipalService principalService;
}
