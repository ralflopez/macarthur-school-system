package com.group11.schoolmanagementsystem.school;

import com.group11.schoolmanagementsystem.exception.ApiRequestException;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping
@CrossOrigin
@Api(tags = "School")
public class SchoolController {
    private SchoolService schoolService;

    @Autowired
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping
    public void welcome(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @GetMapping("/school")
    public ResponseEntity<School> getSchool() {
        School school = schoolService.get();
        return new ResponseEntity<>(school, HttpStatus.OK);
    }
}
