package com.ssitacademy.berezinvv.schooldiary.service;

import com.ssitacademy.berezinvv.schooldiary.model.ClassGroup;
import com.ssitacademy.berezinvv.schooldiary.model.Schedule;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

public interface ScheduleService {

    Schedule create(Schedule schedule);

    Schedule update(Schedule schedule);

    Schedule findById(Long id);

    List<Schedule> findAll();

    void delete(Schedule schedule);

    void delete(Long id);

    List<Schedule> findAllByClassGroup(ClassGroup classGroup);

    List<Schedule> findAllByClassGroupAndDay(ClassGroup classgroup, DayOfWeek day);
}
