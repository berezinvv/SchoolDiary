package com.ssitacademy.berezinvv.schooldiary.exception;

public class SchoolDiaryEntiryNotFoundException extends SchoolDiaryExeption {
    public SchoolDiaryEntiryNotFoundException(Long id, String msg) {
        super("Could not find " + msg + " id = " + id);
    }
}
