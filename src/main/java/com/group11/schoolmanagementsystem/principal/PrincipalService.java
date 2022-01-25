package com.group11.schoolmanagementsystem.principal;

import com.group11.schoolmanagementsystem.exception.ApiRequestException;
import com.group11.schoolmanagementsystem.principal.converter.PrincipalDtoConverter;
import com.group11.schoolmanagementsystem.principal.dto.PrincipalDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrincipalService {
    private PrincipalRepository principalRepository;
    private PrincipalDtoConverter principalDtoConverter;

    @Autowired
    public PrincipalService(PrincipalRepository principalRepository, PrincipalDtoConverter principalDtoConverter) {
        this.principalRepository = principalRepository;
        this.principalDtoConverter = principalDtoConverter;
    }

    public PrincipalDto get() {
        Principal principal = principalRepository.getById(Long.valueOf(1));
        return principalDtoConverter.principalToDto(principal);
    }
}
