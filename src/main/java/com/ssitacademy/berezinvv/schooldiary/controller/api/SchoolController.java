package com.ssitacademy.berezinvv.schooldiary.controller.api;

import com.ssitacademy.berezinvv.schooldiary.dto.SchoolDTO;
import com.ssitacademy.berezinvv.schooldiary.model.School;
import com.ssitacademy.berezinvv.schooldiary.service.SchoolService;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    private ModelMapper modelMapper = new ModelMapper();

    @GetMapping(value = "/schools", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View a list of available School", response = SchoolDTO.class, responseContainer = "List")
    public ResponseEntity<List<SchoolDTO>> findAll() {
        List<School> schools = schoolService.findAll();

        List<SchoolDTO> schoolsDTO = schools.stream().map(school -> modelMapper.map(school, SchoolDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(schoolsDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/schools", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Adding new School", response = SchoolDTO.class)
    public ResponseEntity<SchoolDTO> newSchool(@RequestBody SchoolDTO newSchoolDTO) {
        School school = modelMapper.map(newSchoolDTO, School.class);
        schoolService.create(school);
        SchoolDTO schoolDTO = modelMapper.map(school, SchoolDTO.class);
        return new ResponseEntity<>(schoolDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "/schools/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View one available School", response = SchoolDTO.class)
    public ResponseEntity<SchoolDTO> findOne(@PathVariable Long id) {
        School school = schoolService.findById(id);
        SchoolDTO schoolDTO = modelMapper.map(school, SchoolDTO.class);
        return new ResponseEntity<>(schoolDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/schools/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Replace one available School", response = SchoolDTO.class)
    public ResponseEntity<SchoolDTO> replaceSchool(@RequestBody SchoolDTO newSchoolDTO, @PathVariable Long id) {
        School school = modelMapper.map(newSchoolDTO, School.class);
        school.setId(id);
        schoolService.update(school);

        SchoolDTO schoolDTO = modelMapper.map(school, SchoolDTO.class);
        return new ResponseEntity<>(schoolDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/schools/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete one available School", response = String.class)
    public ResponseEntity<String> deleteSchool(@PathVariable Long id) {
        schoolService.delete(id);
        return new ResponseEntity<String>("{\"info\": \"DELETE Response\"}", HttpStatus.OK);
    }
}
