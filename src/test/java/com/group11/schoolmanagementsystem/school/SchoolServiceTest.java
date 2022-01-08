package com.group11.schoolmanagementsystem.school;

import com.group11.schoolmanagementsystem.exception.ApiRequestException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SchoolServiceTest {

    @Mock
    private SchoolRepository schoolRepository;

    @InjectMocks
    private SchoolService schoolService;

    @Captor
    private ArgumentCaptor<School> schoolArgumentCaptor;

    @Test
    void itCanAddNewSchool() {
        // given
        Long id = Long.valueOf(122474);
        String name = "Alang-Alang Primary School";
        String region = "VIII";
        String division = "Eastern Samar";
        String district = "General Macarthur";

        School newSchool = School.builder()
                .id(id)
                .name(name)
                .region(region)
                .division(division)
                .district(district)
                .build();

        // when
        schoolService.addSchool(newSchool);

        // then
        verify(schoolRepository).save(schoolArgumentCaptor.capture());
        School schoolResponse = schoolArgumentCaptor.getValue();

        assertThat(schoolResponse).isEqualTo(newSchool);
    }

    @Test
    void itWillThrowErrorWhenSchoolAlreadyExists() {
        // given
        School newSchool = School.builder()
                .id(Long.valueOf(122474))
                .name("Alang-Alang Primary School")
                .region("VIII")
                .division("Eastern Samar")
                .district("General Macarthur")
                .build();

        // when
        when(schoolRepository.findById(anyLong())).thenReturn(Optional.of(newSchool));

        // then
        assertThatThrownBy(() -> schoolService.addSchool(newSchool))
                .isInstanceOf(ApiRequestException.class)
                .hasMessage("School already exists");
    }

}