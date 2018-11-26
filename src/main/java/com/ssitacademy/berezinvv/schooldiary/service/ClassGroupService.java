package com.ssitacademy.berezinvv.schooldiary.service;

import com.ssitacademy.berezinvv.schooldiary.model.ClassGroup;
import com.ssitacademy.berezinvv.schooldiary.model.Pupil;
import com.ssitacademy.berezinvv.schooldiary.model.School;

import java.util.List;

public interface ClassGroupService {

    ClassGroup create(ClassGroup classGroup);

    ClassGroup update(ClassGroup classGroup);

    ClassGroup findById(Long id);

    List<ClassGroup> findAll();

    void delete(ClassGroup classGroup);

    void delete(Long id);

    List<ClassGroup> findAllBySchool(School school);

    List<ClassGroup> findAllClassGroupByPupil(Pupil pupil);

}
