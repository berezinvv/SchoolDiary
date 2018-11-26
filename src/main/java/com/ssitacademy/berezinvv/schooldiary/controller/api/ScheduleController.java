package com.ssitacademy.berezinvv.schooldiary.controller.api;

import com.ssitacademy.berezinvv.schooldiary.dto.ScheduleDTO;
import com.ssitacademy.berezinvv.schooldiary.model.ClassGroup;
import com.ssitacademy.berezinvv.schooldiary.model.Schedule;
import com.ssitacademy.berezinvv.schooldiary.service.ClassGroupService;
import com.ssitacademy.berezinvv.schooldiary.service.ScheduleService;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private ClassGroupService classGroupService;

    private ModelMapper modelMapper = new ModelMapper();

    @GetMapping(value = "/schedules/class-group/{id}/day/{day}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View a list of available Schedule by class gropu and day of week", response = ScheduleDTO.class, responseContainer = "List")
    public ResponseEntity<List<ScheduleDTO>> findAllByClassGroupAndDay(@PathVariable Long id,
                                                                       @PathVariable String day) {

        ClassGroup classGroup = classGroupService.findById(id);

        List<Schedule> schedules = scheduleService.findAllByClassGroupAndDay(classGroup, DayOfWeek.valueOf(day));

        List<ScheduleDTO> schedulesDTO = schedules.stream().map(schedule -> modelMapper.map(schedule, ScheduleDTO.class)).collect(Collectors.toList());

        return new ResponseEntity<>(schedulesDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/schedules/class-group/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View a list of available Schedule", response = ScheduleDTO.class, responseContainer = "List")
    public ResponseEntity<List<ScheduleDTO>> findAllByClassGroup(@PathVariable Long id) {

        ClassGroup classGroup = classGroupService.findById(id);
        List<Schedule> schedules = scheduleService.findAllByClassGroup(classGroup);

        List<ScheduleDTO> schedulesDTO = schedules.stream().map(schedule -> modelMapper.map(schedule, ScheduleDTO.class)).collect(Collectors.toList());

        return new ResponseEntity<>(schedulesDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/schedules", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View a list of available Schedule", response = ScheduleDTO.class, responseContainer = "List")
    public ResponseEntity<List<ScheduleDTO>> findAll() {
        List<Schedule> schedules = scheduleService.findAll();

        List<ScheduleDTO> schedulesDTO = schedules.stream().map(schedule -> modelMapper.map(schedule, ScheduleDTO.class)).collect(Collectors.toList());

        return new ResponseEntity<>(schedulesDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/schedules", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Adding new Schedule", response = ScheduleDTO.class)
    public ResponseEntity<ScheduleDTO> newSchedule(@RequestBody ScheduleDTO newScheduleDTO) {
        Schedule schedule = modelMapper.map(newScheduleDTO, Schedule.class);
        scheduleService.create(schedule);
        ScheduleDTO scheduleDTO = modelMapper.map(schedule, ScheduleDTO.class);
        return new ResponseEntity<>(scheduleDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "/schedules/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View one available Schedule", response = ScheduleDTO.class)
    public ResponseEntity<ScheduleDTO> findOne(@PathVariable Long id) {

        Schedule schedule = scheduleService.findById(id);

        ScheduleDTO scheduleDTO = modelMapper.map(schedule, ScheduleDTO.class);
        return new ResponseEntity<>(scheduleDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/schedules/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Replace one available Schedule", response = ScheduleDTO.class)
    public ResponseEntity<ScheduleDTO> replaceSchedule(@RequestBody ScheduleDTO newScheduleDTO, @PathVariable Long id) {
        Schedule schedule = modelMapper.map(newScheduleDTO, Schedule.class);
        schedule.setId(id);
        scheduleService.update(schedule);

        ScheduleDTO scheduleDTO = modelMapper.map(schedule, ScheduleDTO.class);
        return new ResponseEntity<>(scheduleDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/schedules/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete one available Schedule", response = String.class)
    public ResponseEntity<String> deleteSchedule(@PathVariable Long id) {
        scheduleService.delete(id);
        return new ResponseEntity<String>("{\"info\": \"DELETE Response\"}", HttpStatus.OK);
    }
}
