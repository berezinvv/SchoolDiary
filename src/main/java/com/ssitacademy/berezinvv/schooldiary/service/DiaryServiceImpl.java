package com.ssitacademy.berezinvv.schooldiary.service;

import com.ssitacademy.berezinvv.schooldiary.model.ClassGroup;
import com.ssitacademy.berezinvv.schooldiary.model.Diary;
import com.ssitacademy.berezinvv.schooldiary.model.Schedule;
import com.ssitacademy.berezinvv.schooldiary.repository.DiaryRepository;
import com.ssitacademy.berezinvv.schooldiary.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiaryServiceImpl implements DiaryService {

    @Autowired
    private DiaryRepository diaryRepository;

    @Override
    public Diary create(Diary diary) {
        return diaryRepository.save(diary);
    }

    @Override
    public Diary update(Diary diary) {
        return diaryRepository.save(diary);
    }

    @Override
    public Optional<Diary> findById(Long id) {
        return diaryRepository.findById(id);
    }

    @Override
    public List<Diary> findAll() {
        return diaryRepository.findAll();
    }

    @Override
    public void delete(Diary diary) {
        diaryRepository.delete(diary);
    }

    @Override
    public void delete(Long id) {
        diaryRepository.deleteById(id);
    }
}
