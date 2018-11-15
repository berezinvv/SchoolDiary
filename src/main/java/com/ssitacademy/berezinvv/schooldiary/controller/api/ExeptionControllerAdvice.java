package com.ssitacademy.berezinvv.schooldiary.controller.api;


import com.ssitacademy.berezinvv.schooldiary.dto.ErrorDTO;
import com.ssitacademy.berezinvv.schooldiary.exception.EntityNotFoundSchoolDiaryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
@RestController
public class ExeptionControllerAdvice {

    @ResponseBody
    @ExceptionHandler(EntityNotFoundSchoolDiaryException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public final ResponseEntity<ErrorDTO> SchoolDiaryNotFoundHandler(EntityNotFoundSchoolDiaryException ex, WebRequest request) {

        ErrorDTO errorDTO = new ErrorDTO(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }
}
