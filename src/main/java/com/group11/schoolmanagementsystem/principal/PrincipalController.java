package com.group11.schoolmanagementsystem.principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrincipalController {
    private PrincipalService principalService;

    @Autowired
    public PrincipalController(PrincipalService principalService) {
        this.principalService = principalService;
    }
}
