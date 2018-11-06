package com.ssitacademy.berezinvv.schooldiary.service;

import com.ssitacademy.berezinvv.schooldiary.model.Diary;

import java.util.List;
import java.util.Optional;

public interface DiaryService {

    Diary create(Diary diary);

    Diary update(Diary diary);

    Optional<Diary> findById(Long id);

    List<Diary> findAll();

    void delete(Diary diary);

    void delete(Long id);
}
