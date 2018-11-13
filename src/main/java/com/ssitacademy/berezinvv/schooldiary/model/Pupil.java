package com.ssitacademy.berezinvv.schooldiary.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Pupil")
public class Pupil {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "firstName", nullable = false)
    private String firstName;
    @Column(name = "lastName", nullable = false)
    private String lastName;
    @Column(name = "parentName", nullable = false)
    private String parentName;
    @Column(name = "parentPhone", nullable = false)
    private String parentPhone;
    @ManyToMany(mappedBy = "pupils")
    private Set<ClassGroup> classGroups = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pupil")
    private Set<Diary> diaries;

    public Pupil() {
    }

    public Pupil(long id, String firstName, String lastName, String parentName, String parentPhone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.parentName = parentName;
        this.parentPhone = parentPhone;
    }

    public Pupil(long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public Set<ClassGroup> getClassGroups() {
        return classGroups;
    }

    public void setClassGroups(Set<ClassGroup> classGroups) {
        this.classGroups = classGroups;
    }

    public Set<Diary> getDiaries() {
        return diaries;
    }

    public void setDiaries(Set<Diary> diaries) {
        this.diaries = diaries;
    }
}
