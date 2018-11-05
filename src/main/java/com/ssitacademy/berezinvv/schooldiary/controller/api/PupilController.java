package com.ssitacademy.berezinvv.schooldiary.controller.api;

import com.ssitacademy.berezinvv.schooldiary.dto.PupilDTO;
import com.ssitacademy.berezinvv.schooldiary.exception.ServiceNotFoundException;
import com.ssitacademy.berezinvv.schooldiary.model.Pupil;
import com.ssitacademy.berezinvv.schooldiary.service.PupilService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PupilController {

    @Autowired
    PupilService pupilService;

    ModelMapper modelMapper = new ModelMapper();

    @GetMapping(value = "/pupils", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PupilDTO>> findAll() {
        List<Pupil> pupils = pupilService.findAll();

        List<PupilDTO> pupilsDTO = pupils.stream().map(pupil->modelMapper.map(pupil, PupilDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(pupilsDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/pupils", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PupilDTO> newPupil(@RequestBody PupilDTO newPupilDTO) {
        Pupil pupil = modelMapper.map(newPupilDTO, Pupil.class);
        pupilService.create(pupil);
        PupilDTO pupilDTO = modelMapper.map(pupil, PupilDTO.class);
        return new ResponseEntity<>(pupilDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/pupils/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PupilDTO> findOne(@PathVariable Long id) {
        Pupil pupil = pupilService.findById(id)
                .orElseThrow(() -> new ServiceNotFoundException(id, "pupil"));

        PupilDTO pupilDTO = modelMapper.map(pupil, PupilDTO.class);
        return new ResponseEntity<>(pupilDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/pupils/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PupilDTO> replacePupil(@RequestBody PupilDTO newPupilDTO, @PathVariable Long id) {

        Pupil pupil = modelMapper.map(newPupilDTO, Pupil.class);
        pupil.setId(id);
        pupilService.create(pupil);

        PupilDTO pupilDTO = modelMapper.map(pupil, PupilDTO.class);

        return new ResponseEntity<>(pupilDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/pupils/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteSchool(@PathVariable Long id) {
        pupilService.delete(id);
        return new ResponseEntity<String>("{\"info\": \"DELETE Response\"}", HttpStatus.OK);
    }
}
