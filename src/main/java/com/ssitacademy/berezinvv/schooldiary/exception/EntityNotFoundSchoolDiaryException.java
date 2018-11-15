package com.ssitacademy.berezinvv.schooldiary.exception;

public class EntityNotFoundSchoolDiaryException extends SchoolDiaryExeption {
    public EntityNotFoundSchoolDiaryException(Long id, String msg) {
        super("Could not find " + msg + " id = " + id);
    }
}
