package com.ssitacademy.berezinvv.schooldiary.dto;

import com.ssitacademy.berezinvv.schooldiary.model.ClassGroup;
import com.ssitacademy.berezinvv.schooldiary.model.Employee;
import com.ssitacademy.berezinvv.schooldiary.model.Lesson;
import com.ssitacademy.berezinvv.schooldiary.model.Pupil;

import java.sql.Date;

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
