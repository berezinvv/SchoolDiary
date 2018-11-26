package com.ssitacademy.berezinvv.schooldiary.service;


import com.ssitacademy.berezinvv.schooldiary.model.ClassGroup;
import com.ssitacademy.berezinvv.schooldiary.model.Pupil;

import java.util.List;
import java.util.Optional;

public interface PupilService {

    Pupil create(Pupil pupil);

    Pupil update(Pupil pupil);

    Pupil findById(Long id);

    List<Pupil> findAll();

    void delete(Pupil pupil);

    void delete(Long id);

    List<Pupil> findAllPupilByClassGroup(ClassGroup classGroup);

}
