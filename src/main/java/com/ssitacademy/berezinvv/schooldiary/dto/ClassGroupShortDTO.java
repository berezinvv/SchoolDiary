package com.ssitacademy.berezinvv.schooldiary.dto;


import java.sql.Date;
import java.util.Set;

public class ClassGroupShortDTO {

    private long id;
    private String name;
    private Date beginTime;
    private EmployeeDTO teacher;
    private SchoolDTO school;

    public ClassGroupShortDTO() {
    }

    public ClassGroupShortDTO(long id, String name, Date beginTime, EmployeeDTO teacher, SchoolDTO school) {
        this.id = id;
        this.name = name;
        this.beginTime = beginTime;
        this.teacher = teacher;
        this.school = school;
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

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public EmployeeDTO getTeacher() {
        return teacher;
    }

    public void setTeacher(EmployeeDTO teacher) {
        this.teacher = teacher;
    }

    public SchoolDTO getSchool() {
        return school;
    }

    public void setSchool(SchoolDTO school) {
        this.school = school;
    }
}
