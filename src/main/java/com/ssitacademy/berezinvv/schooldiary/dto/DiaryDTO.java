package com.ssitacademy.berezinvv.schooldiary.dto;

import java.util.Date;

public class DiaryDTO {

    private long id;
    private Date date;
    private ClassGroupShortDTO classGroup;
    private LessonDTO lesson;
    private EmployeeDTO teacher;
    private PupilDTO pupil;
    private int grade;

    public DiaryDTO() {
    }

    public DiaryDTO(long id, Date date, ClassGroupShortDTO classGroup, LessonDTO lesson, EmployeeDTO teacher, PupilDTO pupil, int grade) {
        this.id = id;
        this.date = date;
        this.classGroup = classGroup;
        this.lesson = lesson;
        this.teacher = teacher;
        this.pupil = pupil;
        this.grade = grade;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ClassGroupShortDTO getClassGroup() {
        return classGroup;
    }

    public void setClassGroup(ClassGroupShortDTO classGroup) {
        this.classGroup = classGroup;
    }

    public LessonDTO getLesson() {
        return lesson;
    }

    public void setLesson(LessonDTO lesson) {
        this.lesson = lesson;
    }

    public EmployeeDTO getTeacher() {
        return teacher;
    }

    public void setTeacher(EmployeeDTO teacher) {
        this.teacher = teacher;
    }

    public PupilDTO getPupil() {
        return pupil;
    }

    public void setPupil(PupilDTO pupil) {
        this.pupil = pupil;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
