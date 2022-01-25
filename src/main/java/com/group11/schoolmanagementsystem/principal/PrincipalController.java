package com.group11.schoolmanagementsystem.principal;

import com.group11.schoolmanagementsystem.principal.dto.PrincipalDto;
import com.group11.schoolmanagementsystem.teacher.Teacher;
import com.group11.schoolmanagementsystem.teacher.TeacherService;
import com.group11.schoolmanagementsystem.teacher.dto.CreateTeacherDto;
import com.group11.schoolmanagementsystem.teacher.dto.TeacherDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/principal")
@CrossOrigin
public class PrincipalController {
    private PrincipalService principalService;

    @Autowired
    public PrincipalController(PrincipalService principalService) {
        this.principalService = principalService;
    }

    @GetMapping
    public ResponseEntity<PrincipalDto> getPrincipal() {
        PrincipalDto principalDto = principalService.get();
        return new ResponseEntity<>(principalDto, HttpStatus.OK);
    }
}
