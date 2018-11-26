package com.ssitacademy.berezinvv.schooldiary.service;

import com.ssitacademy.berezinvv.schooldiary.dto.DiaryDoubleDTO;
import com.ssitacademy.berezinvv.schooldiary.exception.EntityNotFoundSchoolDiaryException;
import com.ssitacademy.berezinvv.schooldiary.model.ClassGroup;
import com.ssitacademy.berezinvv.schooldiary.model.Diary;
import com.ssitacademy.berezinvv.schooldiary.model.Lesson;
import com.ssitacademy.berezinvv.schooldiary.model.Pupil;
import com.ssitacademy.berezinvv.schooldiary.repository.DiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
    public Diary findById(Long id) {
        return diaryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundSchoolDiaryException(id, "diary"));
    }

    @Override
    public List<Diary> findAll() {
        return diaryRepository.findAll();
    }

    @Override
    public List<Diary> findAllByPupil(Pupil pupil, Pageable pageable) {
        return diaryRepository.findAllByPupil(pupil, pageable);
    }

    @Override
    public void delete(Diary diary) {
        diaryRepository.delete(diary);
    }

    @Override
    public void delete(Long id) {
        diaryRepository.deleteById(id);
    }

    @Override
    public List<Diary> findAllByClassGroup(ClassGroup classgroup) {
        return diaryRepository.findAllByClassGroup(classgroup);
    }

    @Override
    public List<Diary> findAllByPupilAndDay(Pupil pupil, Date date) {
        return diaryRepository.findAllByPupilAndDay(pupil, date);
    }

    @Override
    public List<Diary> findAllByPupil(Pupil pupil) {
        return diaryRepository.findAllByPupil(pupil);
    }

    @Override
    public List<Diary> findAllByPupilAndDayAndLesson(Pupil pupil, Date date, Lesson lesson) {
        return diaryRepository.findAllByPupilAndDayAndLesson(pupil, date, lesson);
    }

    @Override
    public List<DiaryDoubleDTO> getAverageBetweenPeriodByClassGroup(Date periodFrom, Date periodTo, ClassGroup classgroup) {
        return diaryRepository.getAverageBetweenPeriodByClassGroup(periodFrom, periodTo, classgroup);
    }

    @Override
    public List<DiaryDoubleDTO> getAverageBetweenPeriodByPupil(Date periodFrom, Date periodTo, Pupil pupil) {
        return diaryRepository.getAverageBetweenPeriodByPupil(periodFrom, periodTo, pupil);
    }

    @Override
    public List<Diary> findAllByDayAndLessonAndClassGroup(Date date, Lesson lesson, ClassGroup classgroup) {
        return diaryRepository.findAllByDayAndLessonAndClassGroup(lesson, classgroup, date);
    }
}
