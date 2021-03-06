package com.ssitacademy.berezinvv.schooldiary.service;

import com.ssitacademy.berezinvv.schooldiary.exception.EntityNotFoundSchoolDiaryException;
import com.ssitacademy.berezinvv.schooldiary.model.Lesson;
import com.ssitacademy.berezinvv.schooldiary.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Override
    public Lesson create(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    @Override
    public Lesson update(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    @Override
    public Lesson findById(Long id) {
        return lessonRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundSchoolDiaryException(id, "lesson"));
    }

    @Override
    public List<Lesson> findAll() {
        return lessonRepository.findAll();
    }

    @Override
    public void delete(Lesson lesson) {
        lessonRepository.delete(lesson);
    }

    @Override
    public void delete(Long id) {
        lessonRepository.deleteById(id);
    }
}
