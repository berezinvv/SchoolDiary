package com.ssitacademy.berezinvv.schooldiary.exception;

public class ServiceNotFoundException extends RuntimeException {
    public ServiceNotFoundException(Long id, String msg) {
        super("{\"info\": \"Could not find " + msg + " id = " + id + "\"}");
    }
}
