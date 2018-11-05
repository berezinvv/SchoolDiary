package com.ssitacademy.berezinvv.schooldiary.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
@Table(name = "Diary")
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "date", nullable = false)
    private Date date;
    @ManyToOne
    @JoinColumn(name = "classGroup_id", nullable = false)
    private ClassGroup classGroup;
    @ManyToOne
    @JoinColumn(name = "lesson_id", nullable = false)
    private Lesson lesson;
    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Employee teacher;
    @ManyToOne
    @JoinColumn(name = "pupil_id", nullable = false)
    private Pupil pupil;
    @Size(max = 12)
    @Column(name = "grade")
    private int grade;

}
