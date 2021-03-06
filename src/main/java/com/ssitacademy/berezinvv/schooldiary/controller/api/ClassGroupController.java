package com.ssitacademy.berezinvv.schooldiary.controller.api;

import com.ssitacademy.berezinvv.schooldiary.dto.ClassGroupDTO;
import com.ssitacademy.berezinvv.schooldiary.dto.ScheduleDTO;
import com.ssitacademy.berezinvv.schooldiary.model.ClassGroup;
import com.ssitacademy.berezinvv.schooldiary.model.Schedule;
import com.ssitacademy.berezinvv.schooldiary.model.School;
import com.ssitacademy.berezinvv.schooldiary.service.ClassGroupService;
import com.ssitacademy.berezinvv.schooldiary.service.ScheduleService;
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
public class ClassGroupController {

    @Autowired
    private ClassGroupService classGroupService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private SchoolService schoolService;

    private ModelMapper modelMapper = new ModelMapper();

    @GetMapping(value = "/classesSchedule/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View a list of available Schedule by ClassGroup", response = ScheduleDTO.class, responseContainer = "List")
    public ResponseEntity<List<ScheduleDTO>> getScheduleByClassGroupId(@PathVariable Long id) {
        ClassGroup classGroup = classGroupService.findById(id);
        List<Schedule> schedules = scheduleService.findAllByClassGroup(classGroup);

        List<ScheduleDTO> schedulesDTO = schedules.stream().map(schedule -> modelMapper.map(schedule, ScheduleDTO.class)).collect(Collectors.toList());

        return new ResponseEntity<>(schedulesDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/classesBySchool/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View a list of available ClassGroup by School", response = ScheduleDTO.class, responseContainer = "List")
    public ResponseEntity<List<ClassGroupDTO>> getClassGroupBySchool(@PathVariable Long id) {

        School school = schoolService.findById(id);
        List<ClassGroup> classGroups = classGroupService.findAllBySchool(school);

        List<ClassGroupDTO> classGroupDTO = classGroups.stream().map(classGroup -> modelMapper.map(classGroup, ClassGroupDTO.class)).collect(Collectors.toList());

        return new ResponseEntity<>(classGroupDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/classes", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View a list of available ClassGroup", response = ClassGroupDTO.class, responseContainer = "List")
    public ResponseEntity<List<ClassGroupDTO>> findAll() {

        List<ClassGroup> classGroups = classGroupService.findAll();

        List<ClassGroupDTO> classGroupsDTO = classGroups.stream().map(classGroup -> modelMapper.map(classGroup, ClassGroupDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(classGroupsDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/classes", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Adding new ClassGroups", response = ClassGroupDTO.class)
    public ResponseEntity<ClassGroupDTO> newClassGroup(@RequestBody ClassGroupDTO newClassGroupDTO) {
        ClassGroup classGroup = modelMapper.map(newClassGroupDTO, ClassGroup.class);
        classGroupService.create(classGroup);
        ClassGroupDTO classGroupDTO = modelMapper.map(classGroup, ClassGroupDTO.class);
        return new ResponseEntity<>(classGroupDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "/classes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View one available ClassGroup", response = ClassGroupDTO.class)
    public ResponseEntity<ClassGroupDTO> findOne(@PathVariable Long id) {
        ClassGroup classGroup = classGroupService.findById(id);

        ClassGroupDTO classGroupDTO = modelMapper.map(classGroup, ClassGroupDTO.class);
        return new ResponseEntity<>(classGroupDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/classes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Replace one available ClassGroup", response = ClassGroupDTO.class)
    public ResponseEntity<ClassGroupDTO> replaceClassGroup(@RequestBody ClassGroupDTO newClassGroupDTO, @PathVariable Long id) {
        ClassGroup classGroup = modelMapper.map(newClassGroupDTO, ClassGroup.class);
        classGroup.setId(id);
        classGroupService.update(classGroup);

        ClassGroupDTO classGroupDTO = modelMapper.map(classGroup, ClassGroupDTO.class);

        return new ResponseEntity<>(classGroupDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/classes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete one available ClassGroup", response = String.class)
    public ResponseEntity<String> deleteClassGroup(@PathVariable Long id) {
        classGroupService.delete(id);
        return new ResponseEntity<String>("{\"info\": \"DELETE Response\"}", HttpStatus.OK);
    }
}
