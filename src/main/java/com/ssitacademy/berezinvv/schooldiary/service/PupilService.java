package com.ssitacademy.berezinvv.schooldiary.service;


import com.ssitacademy.berezinvv.schooldiary.model.Pupil;

import java.util.List;
import java.util.Optional;

public interface PupilService {

    Pupil create(Pupil pupil);

    Pupil update(Pupil pupil);

    Optional<Pupil> findById(Long id);

    List<Pupil> findAll();

    void delete(Pupil pupil);

    void delete(Long id);

}
