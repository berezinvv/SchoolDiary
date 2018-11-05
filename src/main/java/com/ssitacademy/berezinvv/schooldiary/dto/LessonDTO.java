package com.ssitacademy.berezinvv.schooldiary.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssitacademy.berezinvv.schooldiary.model.Diary;
import com.ssitacademy.berezinvv.schooldiary.model.Schedule;

import javax.persistence.*;
import java.util.Set;

public class LessonDTO {
    private long id;
    private String name;

    public LessonDTO() {
    }

    public LessonDTO(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
