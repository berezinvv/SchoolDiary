package com.ssitacademy.berezinvv.schooldiary.dto;

import com.ssitacademy.berezinvv.schooldiary.model.ClassGroup;
import com.ssitacademy.berezinvv.schooldiary.model.Employee;
import com.ssitacademy.berezinvv.schooldiary.model.Lesson;
import com.ssitacademy.berezinvv.schooldiary.model.Pupil;

import java.sql.Date;

public class DiaryDoubleDTO {

    private long id;
    private Date date;
    private ClassGroup classGroup;
    private Lesson lesson;
    private Employee teacher;
    private Pupil pupil;
    private double grade;

    public DiaryDoubleDTO() {
    }

    public DiaryDoubleDTO(double grade, Pupil pupil, Lesson lesson, ClassGroup classGroup) {
        this.classGroup = classGroup;
        this.lesson = lesson;
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

    public ClassGroup getClassGroup() {
        return classGroup;
    }

    public void setClassGroup(ClassGroup classGroup) {
        this.classGroup = classGroup;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Employee getTeacher() {
        return teacher;
    }

    public void setTeacher(Employee teacher) {
        this.teacher = teacher;
    }

    public Pupil getPupil() {
        return pupil;
    }

    public void setPupil(Pupil pupil) {
        this.pupil = pupil;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}
