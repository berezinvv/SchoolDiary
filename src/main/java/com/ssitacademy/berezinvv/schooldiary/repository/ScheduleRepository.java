package com.ssitacademy.berezinvv.schooldiary.repository;

import com.ssitacademy.berezinvv.schooldiary.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.DayOfWeek;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("select s from Schedule s where s.classGroup =:classgroup")
    List<Schedule> findAllByClassGroup(@Param("classgroup") ClassGroup classgroup);

    @Query("select s from Schedule s where s.day =:day and s.classGroup =:classgroup")
    List<Schedule> findAllByClassGroupAndDay(@Param("classgroup") ClassGroup classgroup, @Param("day") DayOfWeek day);
}
