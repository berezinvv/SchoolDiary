package com.ssitacademy.berezinvv.schooldiary.model;


import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ClassGroup")
public class ClassGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "begin_time", nullable = false)
    private Date beginTime;
    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Employee teacher;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classGroup")
    private Set<Schedule> schedules;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classGroup")
    private Set<Diary> diaries;
    @ManyToOne
    @JoinColumn(name = "school_id", nullable = false)
    private School school;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "ClassGroup_Pupil",
            joinColumns = {@JoinColumn(name = "classGroup_id")},
            inverseJoinColumns = {@JoinColumn(name = "pupil_id")}
    )
    Set<Pupil> pupils = new HashSet<>();

    public ClassGroup() {
    }

    public ClassGroup(long id, String name, Date beginTime, Employee teacher, School school) {
        this.id = id;
        this.name = name;
        this.beginTime = beginTime;
        this.teacher = teacher;
        this.school = school;
    }

    public ClassGroup(long id, String name) {
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

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Employee getTeacher() {
        return teacher;
    }

    public void setTeacher(Employee teacher) {
        this.teacher = teacher;
    }

    public Set<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(Set<Schedule> schedules) {
        this.schedules = schedules;
    }

    public Set<Diary> getDiaries() {
        return diaries;
    }

    public void setDiaries(Set<Diary> diaries) {
        this.diaries = diaries;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Set<Pupil> getPupils() {
        return pupils;
    }

    public void setPupils(Set<Pupil> pupils) {
        this.pupils = pupils;
    }
}
