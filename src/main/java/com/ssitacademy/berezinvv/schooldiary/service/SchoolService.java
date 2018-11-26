package com.ssitacademy.berezinvv.schooldiary.service;

import com.ssitacademy.berezinvv.schooldiary.model.School;

import java.util.List;
import java.util.Optional;

public interface SchoolService {

    School create(School school);

    School update(School school);

    School findById(Long id);

    List<School> findAll();

    void delete(School school);

    void delete(Long id);

}
