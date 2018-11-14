package com.ssitacademy.berezinvv.schooldiary.repository;

import com.ssitacademy.berezinvv.schooldiary.dto.DiaryDoubleDTO;
import com.ssitacademy.berezinvv.schooldiary.model.ClassGroup;
import com.ssitacademy.berezinvv.schooldiary.model.Diary;
import com.ssitacademy.berezinvv.schooldiary.model.Lesson;
import com.ssitacademy.berezinvv.schooldiary.model.Pupil;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

    @Query("select s from Diary s where s.classGroup =:classgroup")
    List<Diary> findAllByClassGroup(@Param("classgroup") ClassGroup classgroup);

    @Query("select s from Diary s where s.pupil =:pupil and s.date =:date")
    List<Diary> findAllByPupilAndDay(@Param("pupil") Pupil pupil, @Param("date") Date date);

    @Query("select s from Diary s where s.pupil =:pupil order by s.date desc")
    List<Diary> findAllByPupil(@Param("pupil") Pupil pupil);

    @Query("select d from Diary as d where d.pupil =:pupil")
    List<Diary> findAllByPupil(@Param("pupil") Pupil pupil, Pageable pageable);

    @Query("select s from Diary s where s.pupil =:pupil and s.date =:date and s.lesson =:lesson")
    List<Diary> findAllByPupilAndDayAndLesson(@Param("pupil") Pupil pupil, @Param("date") Date date, @Param("lesson") Lesson lesson);

    @Query("select s from Diary s where s.lesson =:lesson and s.classGroup =:classgroup and s.date =:date")
    List<Diary> findAllByDayAndLessonAndClassGroup(@Param("lesson") Lesson lesson, @Param("classgroup") ClassGroup classgroup, @Param("date") Date date);

    @Query("select new com.ssitacademy.berezinvv.schooldiary.dto.DiaryDoubleDTO(AVG(d.grade) as grade, d.pupil as pupil, d.lesson as lesson, d.classGroup as classGroup) from Diary as d where d.classGroup =:classgroup " +
            " and d.date >=:periodFrom and d.date <=:periodTo group by d.pupil, d.lesson, d.classGroup")
    List<DiaryDoubleDTO> getAverageBetweenPeriodByClassGroup(@Param("periodFrom") Date periodFrom, @Param("periodTo") Date periodTo,
                                                             @Param("classgroup") ClassGroup classgroup);

    @Query("select new com.ssitacademy.berezinvv.schooldiary.dto.DiaryDoubleDTO(AVG(d.grade) as grade, d.pupil as pupil, d.lesson as lesson, d.classGroup as classGroup) from Diary as d where d.pupil =:pupil " +
            " and d.date >=:periodFrom and d.date <=:periodTo group by d.pupil, d.lesson, d.classGroup")
    List<DiaryDoubleDTO> getAverageBetweenPeriodByPupil(@Param("periodFrom") Date periodFrom, @Param("periodTo") Date periodTo,
                                                        @Param("pupil") Pupil pupil);
}
