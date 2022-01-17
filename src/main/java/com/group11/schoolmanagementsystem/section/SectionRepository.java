package com.group11.schoolmanagementsystem.section;

import com.group11.schoolmanagementsystem.section.dto.SectionDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
    Optional<Section> findByAdviser_Id(Long adviserId);
}
