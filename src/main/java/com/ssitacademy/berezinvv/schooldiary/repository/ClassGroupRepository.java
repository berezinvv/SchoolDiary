package com.ssitacademy.berezinvv.schooldiary.repository;

import com.ssitacademy.berezinvv.schooldiary.model.ClassGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassGroupRepository extends JpaRepository<ClassGroup, Long> {
}
