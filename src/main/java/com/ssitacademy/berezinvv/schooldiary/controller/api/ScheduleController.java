package com.ssitacademy.berezinvv.schooldiary.controller.api;

import com.ssitacademy.berezinvv.schooldiary.dto.ScheduleDTO;
import com.ssitacademy.berezinvv.schooldiary.exception.ServiceNotFoundException;
import com.ssitacademy.berezinvv.schooldiary.model.Schedule;
import com.ssitacademy.berezinvv.schooldiary.service.ScheduleService;
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
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    ModelMapper modelMapper = new ModelMapper();

    @GetMapping(value = "/schedules", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ScheduleDTO>> findAll() {
        List<ScheduleDTO> schedulesDTO = new ArrayList<>();
        List<Schedule> schedules = scheduleService.findAll();

        schedulesDTO = schedules.stream().map(schedule->modelMapper.map(schedule, ScheduleDTO.class)).collect(Collectors.toList());

        return new ResponseEntity<>(schedulesDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/schedules", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScheduleDTO> newSchedule(@RequestBody ScheduleDTO newScheduleDTO) {
        Schedule schedule = modelMapper.map(newScheduleDTO, Schedule.class);
        scheduleService.create(schedule);
        ScheduleDTO scheduleDTO = modelMapper.map(schedule, ScheduleDTO.class);
        return new ResponseEntity<>(scheduleDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/schedules/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScheduleDTO> findOne(@PathVariable Long id) {

        Schedule schedule = scheduleService.findById(id)
                .orElseThrow(() -> new ServiceNotFoundException(id, "schedule"));

        ScheduleDTO scheduleDTO = modelMapper.map(schedule, ScheduleDTO.class);
        return new ResponseEntity<>(scheduleDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/schedules/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScheduleDTO> replaceSchedule(@RequestBody ScheduleDTO newScheduleDTO, @PathVariable Long id) {
        Schedule schedule = modelMapper.map(newScheduleDTO, Schedule.class);
        schedule.setId(id);
        scheduleService.create(schedule);

        ScheduleDTO scheduleDTO = modelMapper.map(schedule, ScheduleDTO.class);
        return new ResponseEntity<>(scheduleDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/schedules/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteSchedule(@PathVariable Long id) {
        scheduleService.delete(id);
        return new ResponseEntity<String>("{\"info\": \"DELETE Response\"}", HttpStatus.OK);
    }
}
