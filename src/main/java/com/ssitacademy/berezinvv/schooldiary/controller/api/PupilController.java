package com.ssitacademy.berezinvv.schooldiary.controller.api;

import com.ssitacademy.berezinvv.schooldiary.dto.ClassGroupDTO;
import com.ssitacademy.berezinvv.schooldiary.dto.PupilDTO;
import com.ssitacademy.berezinvv.schooldiary.dto.ScheduleDTO;
import com.ssitacademy.berezinvv.schooldiary.exception.ServiceNotFoundException;
import com.ssitacademy.berezinvv.schooldiary.model.ClassGroup;
import com.ssitacademy.berezinvv.schooldiary.model.Pupil;
import com.ssitacademy.berezinvv.schooldiary.model.Schedule;
import com.ssitacademy.berezinvv.schooldiary.service.ClassGroupService;
import com.ssitacademy.berezinvv.schooldiary.service.PupilService;
import com.ssitacademy.berezinvv.schooldiary.service.ScheduleService;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PupilController {

    @Autowired
    private PupilService pupilService;
    @Autowired
    private ClassGroupService classGroupService;

    ModelMapper modelMapper = new ModelMapper();

    @GetMapping(value = "/pupilClassGroup/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View a list of available ClassGroup by Pupil", response = Iterable.class)
    public ResponseEntity<List<ClassGroupDTO>> getScheduleByClassGroupId(@PathVariable Long id) {
        Pupil pupil = pupilService.findById(id)
                .orElseThrow(() -> new ServiceNotFoundException(id, "pupil"));
        List<ClassGroup> classGroups = classGroupService.findAllClassGroupByPupil(pupil);

        List<ClassGroupDTO> classGroupsDTO = classGroups.stream().map(classGroup -> modelMapper.map(classGroup, ClassGroupDTO.class)).collect(Collectors.toList());

        return new ResponseEntity<>(classGroupsDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/pupils", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View a list of available Pupil", response = Iterable.class)
    public ResponseEntity<List<PupilDTO>> findAll() {
        List<Pupil> pupils = pupilService.findAll();

        List<PupilDTO> pupilsDTO = pupils.stream().map(pupil->modelMapper.map(pupil, PupilDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(pupilsDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/pupils", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Adding new Pupil", response = Iterable.class)
    public ResponseEntity<PupilDTO> newPupil(@RequestBody PupilDTO newPupilDTO) {
        Pupil pupil = modelMapper.map(newPupilDTO, Pupil.class);
        pupilService.create(pupil);
        PupilDTO pupilDTO = modelMapper.map(pupil, PupilDTO.class);
        return new ResponseEntity<>(pupilDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/pupils/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View one available Pupil", response = Iterable.class)
    public ResponseEntity<PupilDTO> findOne(@PathVariable Long id) {
        Pupil pupil = pupilService.findById(id)
                .orElseThrow(() -> new ServiceNotFoundException(id, "pupil"));

        PupilDTO pupilDTO = modelMapper.map(pupil, PupilDTO.class);
        return new ResponseEntity<>(pupilDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/pupils/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Replace one available Pupil", response = Iterable.class)
    public ResponseEntity<PupilDTO> replacePupil(@RequestBody PupilDTO newPupilDTO, @PathVariable Long id) {

        Pupil pupil = modelMapper.map(newPupilDTO, Pupil.class);
        pupil.setId(id);
        pupilService.create(pupil);

        PupilDTO pupilDTO = modelMapper.map(pupil, PupilDTO.class);

        return new ResponseEntity<>(pupilDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/pupils/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete one available Pupil", response = Iterable.class)
    public ResponseEntity<String> deleteSchool(@PathVariable Long id) {
        pupilService.delete(id);
        return new ResponseEntity<String>("{\"info\": \"DELETE Response\"}", HttpStatus.OK);
    }
}
