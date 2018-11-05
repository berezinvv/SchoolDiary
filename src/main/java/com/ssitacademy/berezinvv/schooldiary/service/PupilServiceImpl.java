package com.ssitacademy.berezinvv.schooldiary.service;

import com.ssitacademy.berezinvv.schooldiary.model.Pupil;
import com.ssitacademy.berezinvv.schooldiary.repository.PupilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<Pupil> findById(Long id) {
        return pupilRepository.findById(id);
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
}
