package com.ssitacademy.berezinvv.schooldiary.service;

import com.ssitacademy.berezinvv.schooldiary.model.ClassGroup;
import com.ssitacademy.berezinvv.schooldiary.model.Pupil;
import com.ssitacademy.berezinvv.schooldiary.model.School;
import com.ssitacademy.berezinvv.schooldiary.repository.ClassGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassGroupServiceImpl implements ClassGroupService {

    @Autowired
    private ClassGroupRepository classGroupRepository;

    @Override
    public ClassGroup create(ClassGroup classGroup) {
        return classGroupRepository.save(classGroup);
    }

    @Override
    public ClassGroup update(ClassGroup classGroup) {
        return classGroupRepository.save(classGroup);
    }

    @Override
    public Optional<ClassGroup> findById(Long id) {
        return classGroupRepository.findById(id);
    }

    @Override
    public List<ClassGroup> findAll() {
        return classGroupRepository.findAll();
    }

    @Override
    public void delete(ClassGroup classGroup) {
        classGroupRepository.delete(classGroup);
    }

    @Override
    public void delete(Long id) {
        classGroupRepository.deleteById(id);
    }

    @Override
    public List<ClassGroup> findAllBySchool(School school) {
        return classGroupRepository.findAllBySchool(school);
    }

    @Override
    public List<ClassGroup> findAllClassGroupByPupil(Pupil pupil) {
        return classGroupRepository.findAllClassGroupByPupil(pupil);
    }
}
