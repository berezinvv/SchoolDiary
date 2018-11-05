package com.ssitacademy.berezinvv.schooldiary.model;

import javax.persistence.*;

@Entity
@Table(name = "Schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "index_number", nullable = false)
    private int indexNumber;
    @ManyToOne
    @JoinColumn(name = "classGroup_id", nullable = false)
    private ClassGroup classGroup;
    @Column(name = "day", nullable = false)
    @Enumerated(EnumType.STRING)
    private Day day;
    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Employee teacher;
    @ManyToOne
    @JoinColumn(name = "lesson_id", nullable = false)
    private Lesson lesson;

    public Schedule() {
    }

    public Schedule(long id, int indexNumber, ClassGroup classGroup, Day day, Employee teacher, Lesson lesson) {
        this.id = id;
        this.indexNumber = indexNumber;
        this.classGroup = classGroup;
        this.day = day;
        this.teacher = teacher;
        this.lesson = lesson;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(int indexNumber) {
        this.indexNumber = indexNumber;
    }

    public ClassGroup getClassGroup() {
        return classGroup;
    }

    public void setClassGroup(ClassGroup classGroup) {
        this.classGroup = classGroup;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public Employee getTeacher() {
        return teacher;
    }

    public void setTeacher(Employee teacher) {
        this.teacher = teacher;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }
}
