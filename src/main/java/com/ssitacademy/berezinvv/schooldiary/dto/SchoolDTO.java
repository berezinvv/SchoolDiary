package com.ssitacademy.berezinvv.schooldiary.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssitacademy.berezinvv.schooldiary.model.ClassGroup;
import com.ssitacademy.berezinvv.schooldiary.model.Employee;

import javax.persistence.*;
import java.util.Set;

public class SchoolDTO {

    private long id;
    private String name;
    private EmployeeDTO director;
    private String phone;

    public SchoolDTO() {
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

    public EmployeeDTO getDirector() {
        return director;
    }

    public void setDirector(EmployeeDTO director) {
        this.director = director;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
