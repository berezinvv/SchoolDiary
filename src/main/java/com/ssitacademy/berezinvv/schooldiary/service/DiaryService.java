package com.ssitacademy.berezinvv.schooldiary.service;

import com.ssitacademy.berezinvv.schooldiary.dto.DiaryDoubleDTO;
import com.ssitacademy.berezinvv.schooldiary.dto.DiaryPageDTO;
import com.ssitacademy.berezinvv.schooldiary.model.ClassGroup;
import com.ssitacademy.berezinvv.schooldiary.model.Diary;
import com.ssitacademy.berezinvv.schooldiary.model.Lesson;
import com.ssitacademy.berezinvv.schooldiary.model.Pupil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface DiaryService {

    Diary create(Diary diary);

    Diary update(Diary diary);

    Optional<Diary> findById(Long id);

    List<Diary> findAll();

    void delete(Diary diary);

    void delete(Long id);

    List<Diary> findAllByClassGroup(ClassGroup classgroup);

    List<Diary> findAllByPupilAndDay(Pupil pupil, Date date);

    List<Diary> findAllByPupil(Pupil pupil);

    List<Diary> findAllByPupil(Pupil pupil, Pageable pageable);

    List<Diary> findAllByPupilAndDayAndLesson(Pupil pupil, Date date, Lesson lesson);

    List<DiaryDoubleDTO> getAverageBetweenPeriodByClassGroup(Date periodFrom, Date periodTo, ClassGroup classgroup);

    List<DiaryDoubleDTO> getAverageBetweenPeriodByPupil(Date periodFrom, Date periodTo, Pupil pupil);

    List<Diary> findAllByDayAndLessonAndClassGroup(Date date, Lesson lesson, ClassGroup classgroup);
}
