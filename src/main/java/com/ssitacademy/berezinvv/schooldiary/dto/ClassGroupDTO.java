package com.ssitacademy.berezinvv.schooldiary.dto;


import com.ssitacademy.berezinvv.schooldiary.model.Employee;
import com.ssitacademy.berezinvv.schooldiary.model.Pupil;
import com.ssitacademy.berezinvv.schooldiary.model.School;

import java.sql.Date;
import java.util.Set;

public class ClassGroupDTO {

    private long id;
    private String name;
    private Date beginTime;
    private EmployeeDTO teacher;
    private SchoolDTO school;
    private Set<PupilDTO> pupils;

    public ClassGroupDTO() {
    }

    public ClassGroupDTO(long id, String name, Date beginTime, EmployeeDTO teacher, SchoolDTO school, Set<PupilDTO> pupils) {
        this.id = id;
        this.name = name;
        this.beginTime = beginTime;
        this.teacher = teacher;
        this.school = school;
        this.pupils = pupils;
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

    public Set<PupilDTO> getPupils() {
        return pupils;
    }

    public void setPupils(Set<PupilDTO> pupils) {
        this.pupils = pupils;
    }
}
