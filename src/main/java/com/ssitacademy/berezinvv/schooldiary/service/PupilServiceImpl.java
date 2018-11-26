package com.ssitacademy.berezinvv.schooldiary.service;

import com.ssitacademy.berezinvv.schooldiary.exception.EntityNotFoundSchoolDiaryException;
import com.ssitacademy.berezinvv.schooldiary.model.ClassGroup;
import com.ssitacademy.berezinvv.schooldiary.model.Pupil;
import com.ssitacademy.berezinvv.schooldiary.repository.PupilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PupilServiceImpl implements PupilService {

    @Autowired
    private PupilRepository pupilRepository;

    @Override
    public Pupil create(Pupil pupil) {
        return pupilRepository.save(pupil);
    }

    @Override
    public Pupil update(Pupil pupil) {
        return pupilRepository.save(pupil);
    }

    @Override
    public Pupil findById(Long id) {
        return pupilRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundSchoolDiaryException(id, "pupil"));
    }

    @Override
    public List<Pupil> findAll() {
        return pupilRepository.findAll();
    }

    @Override
    public void delete(Pupil pupil) {
        pupilRepository.delete(pupil);
    }

    @Override
    public void delete(Long id) {
        pupilRepository.deleteById(id);
    }

    @Override
    public List<Pupil> findAllPupilByClassGroup(ClassGroup classGroup) {
        return pupilRepository.findAllPupilByClassGroup(classGroup);
    }
}
