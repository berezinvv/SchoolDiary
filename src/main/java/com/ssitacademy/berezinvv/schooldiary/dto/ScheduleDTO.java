package com.ssitacademy.berezinvv.schooldiary.dto;

import java.time.DayOfWeek;

public class ScheduleDTO {
    private long id;
    private int indexNumber;
    private ClassGroupShortDTO classGroup;
    private DayOfWeek day;
    private EmployeeDTO teacher;
    private LessonDTO lesson;

    public ScheduleDTO() {
    }

    public ScheduleDTO(long id, int indexNumber, ClassGroupShortDTO classGroup, DayOfWeek day, EmployeeDTO teacher, LessonDTO lesson) {
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

    public ClassGroupShortDTO getClassGroup() {
        return classGroup;
    }

    public void setClassGroup(ClassGroupShortDTO classGroup) {
        this.classGroup = classGroup;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
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
