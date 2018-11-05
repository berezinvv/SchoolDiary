package com.ssitacademy.berezinvv.schooldiary.service;

import com.ssitacademy.berezinvv.schooldiary.model.Lesson;

import java.util.List;
import java.util.Optional;

public interface LessonService {

    Lesson create(Lesson lesson);

    Lesson update(Lesson lesson);

    Optional<Lesson> findById(Long id);

    List<Lesson> findAll();

    void delete(Lesson lesson);

    void delete(Long id);

}
