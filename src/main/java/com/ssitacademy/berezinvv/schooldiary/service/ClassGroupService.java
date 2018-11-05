package com.ssitacademy.berezinvv.schooldiary.service;

import com.ssitacademy.berezinvv.schooldiary.model.ClassGroup;

import java.util.List;
import java.util.Optional;

public interface ClassGroupService {

    ClassGroup create(ClassGroup classGroup);

    ClassGroup update(ClassGroup classGroup);

    Optional<ClassGroup> findById(Long id);

    List<ClassGroup> findAll();

    void delete(ClassGroup classGroup);

    void delete(Long id);

}
