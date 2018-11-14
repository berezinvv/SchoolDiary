package com.ssitacademy.berezinvv.schooldiary.dto;

import com.ssitacademy.berezinvv.schooldiary.model.Lesson;

import java.util.Date;

public class DiaryPageDTO {

    private long id;
    private Date date;
    private Lesson lesson;
    private int grade;

    public DiaryPageDTO() {
    }

    public DiaryPageDTO(long id, Date date, Lesson lesson, int grade) {
        this.id = id;
        this.date = date;
        this.lesson = lesson;
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

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
