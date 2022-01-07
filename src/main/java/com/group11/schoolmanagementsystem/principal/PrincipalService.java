package com.group11.schoolmanagementsystem.principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrincipalService {
    private PrincipalRepository principalRepository;

    @Autowired
    public PrincipalService(PrincipalRepository principalRepository) {
        this.principalRepository = principalRepository;
    }
}
