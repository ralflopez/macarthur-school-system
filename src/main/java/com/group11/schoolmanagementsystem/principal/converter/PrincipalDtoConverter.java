package com.group11.schoolmanagementsystem.principal.converter;

import com.group11.schoolmanagementsystem.principal.Principal;
import com.group11.schoolmanagementsystem.principal.dto.PrincipalDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PrincipalDtoConverter {
    private ModelMapper modelMapper;

    @Autowired
    public PrincipalDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PrincipalDto principalToDto(Principal principal) {
        return modelMapper.map(principal, PrincipalDto.class);
    }
}
