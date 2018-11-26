package com.ssitacademy.berezinvv.schooldiary.service;

import com.ssitacademy.berezinvv.schooldiary.exception.EntityNotFoundSchoolDiaryException;
import com.ssitacademy.berezinvv.schooldiary.model.School;
import com.ssitacademy.berezinvv.schooldiary.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

    @Override
    public School create(School school) {
        return schoolRepository.save(school);
    }

    @Override
    public School update(School school) {
        return schoolRepository.save(school);
    }

    @Override
    public School findById(Long id) {
        return schoolRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundSchoolDiaryException(id, "school"));
    }

    @Override
    public List<School> findAll() {
        return schoolRepository.findAll();
    }

    @Override
    public void delete(School school) {
        schoolRepository.delete(school);
    }

    @Override
    public void delete(Long id) {
        schoolRepository.deleteById(id);
    }
}
