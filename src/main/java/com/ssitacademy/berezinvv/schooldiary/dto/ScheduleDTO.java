package com.ssitacademy.berezinvv.schooldiary.dto;

import com.ssitacademy.berezinvv.schooldiary.model.ClassGroup;
import com.ssitacademy.berezinvv.schooldiary.model.Day;
import com.ssitacademy.berezinvv.schooldiary.model.Employee;
import com.ssitacademy.berezinvv.schooldiary.model.Lesson;

import javax.persistence.*;

public class ScheduleDTO {
    private long id;
    private int indexNumber;
    private ClassGroupDTO classGroup;
    private Day day;
    private EmployeeDTO teacher;
    private LessonDTO lesson;

    public ScheduleDTO() {
    }

    public ScheduleDTO(long id, int indexNumber, ClassGroupDTO classGroup, Day day, EmployeeDTO teacher, LessonDTO lesson) {
        this.id = id;
        this.indexNumber = indexNumber;
        this.classGroup = classGroup;
        this.day = day;
        this.teacher = teacher;
        this.lesson = lesson;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(int indexNumber) {
        this.indexNumber = indexNumber;
    }

    public ClassGroupDTO getClassGroup() {
        return classGroup;
    }

    public void setClassGroup(ClassGroupDTO classGroup) {
        this.classGroup = classGroup;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public EmployeeDTO getTeacher() {
        return teacher;
    }

    public void setTeacher(EmployeeDTO teacher) {
        this.teacher = teacher;
    }

    public LessonDTO getLesson() {
        return lesson;
    }

    public void setLesson(LessonDTO lesson) {
        this.lesson = lesson;
    }
}
