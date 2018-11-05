package com.ssitacademy.berezinvv.schooldiary.dto;

public class PupilDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String parentName;
    private String parentPhone;

    public PupilDTO() {
    }

    public PupilDTO(long id, String firstName, String lastName, String parentName, String parentPhone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.parentName = parentName;
        this.parentPhone = parentPhone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentPhone() {
        return parentPhone;
    }

    public void setParentPhone(String parentPhone) {
        this.parentPhone = parentPhone;
    }
}
