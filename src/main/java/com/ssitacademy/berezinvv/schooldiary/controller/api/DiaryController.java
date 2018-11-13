package com.ssitacademy.berezinvv.schooldiary.controller.api;

import com.ssitacademy.berezinvv.schooldiary.dto.DiaryDTO;
import com.ssitacademy.berezinvv.schooldiary.dto.DiaryDoubleDTO;
import com.ssitacademy.berezinvv.schooldiary.exception.ServiceNotFoundException;
import com.ssitacademy.berezinvv.schooldiary.model.*;
import com.ssitacademy.berezinvv.schooldiary.service.*;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class DiaryController {

    @Autowired
    private DiaryService diaryService;
    @Autowired
    PupilService pupilService;
    @Autowired
    LessonService lessonService;
    @Autowired
    ClassGroupService classGroupService;
    @Autowired
    EmployeeService employeeService;

    ModelMapper modelMapper = new ModelMapper();

    @GetMapping(value = "/diaries/date/{date}/lesson/{lesson_id}/class-group/{class_group_id}/teacher/{teacher_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View a list of diaries by date and lesson", response = DiaryDTO.class, responseContainer = "List")
    public ResponseEntity<List<DiaryDTO>> findDiaryByLessonAndDateAndClassGroup(@PathVariable String date,
                                                                                @PathVariable Long lesson_id,
                                                                                @PathVariable Long class_group_id,
                                                                                @PathVariable Long teacher_id) {

        Lesson lesson = lessonService.findById(lesson_id).orElseThrow(() -> new ServiceNotFoundException(lesson_id, "lesson"));
        ClassGroup classGroup = classGroupService.findById(class_group_id).orElseThrow(() -> new ServiceNotFoundException(class_group_id, "classGroup"));
        Employee employee = employeeService.findById(teacher_id)
                .orElseThrow(() -> new ServiceNotFoundException(teacher_id, "employee"));
        List<Pupil> pupils = pupilService.findAllPupilByClassGroup(classGroup);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        java.util.Date currentDate = null;
        try {
            currentDate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        //collection with grades
        List<Diary> diaries = diaryService.findAllByDayAndLessonAndClassGroup(currentDate, lesson, classGroup);

        //add to list another pupils
        for (Pupil pupil : pupils) {
            boolean isContain = diaries.stream().anyMatch(d -> d.getPupil().equals(pupil));
            if (!isContain) {
                Diary newDiary = new Diary();
                newDiary.setDate(currentDate);
                newDiary.setPupil(pupil);
                newDiary.setLesson(lesson);
                newDiary.setTeacher(employee);
                newDiary.setClassGroup(classGroup);
                diaries.add(newDiary);
            }
        }

        diaries.sort(Comparator.comparingLong(d -> d.getPupil().getId()));

        List<DiaryDTO> diariesDTO = diaries.stream().map(diary -> modelMapper.map(diary, DiaryDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(diariesDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/avgdiaries-pupil/{pupil_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View a list of average diaries between periods by pupil", response = DiaryDTO.class, responseContainer = "List")
    public ResponseEntity<List<DiaryDTO>> getAverageBetweenPeriodByPupil(@PathVariable Long pupil_id,
                                                                         @RequestParam String periodFrom,
                                                                         @RequestParam String periodTo) {
        Pupil pupil = pupilService.findById(pupil_id).orElseThrow(() -> new ServiceNotFoundException(pupil_id, "pupil"));

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        java.util.Date dateFrom = null;
        java.util.Date dateTo = null;
        try {
            dateFrom = sdf.parse(periodFrom);
            dateTo = sdf.parse(periodTo);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<DiaryDoubleDTO> diaries = new ArrayList<>();
        if (dateFrom != null && dateTo != null) {
            diaries = diaryService.getAverageBetweenPeriodByPupil(dateFrom, dateTo, pupil);
        }

        diaries.sort(Comparator.comparingLong(d -> d.getPupil().getId()));
        List<DiaryDTO> diariesDTO = diaries.stream().map(diary -> modelMapper.map(diary, DiaryDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(diariesDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/avgdiaries/{classGroup_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View a list of average diaries between periods by class group", response = DiaryDTO.class, responseContainer = "List")
    public ResponseEntity<List<DiaryDTO>> getAverageBetweenPeriodByClassGroup(@PathVariable Long classGroup_id,
                                                                              @RequestParam String periodFrom,
                                                                              @RequestParam String periodTo) {
        ClassGroup classGroup = classGroupService.findById(classGroup_id).orElseThrow(() -> new ServiceNotFoundException(classGroup_id, "classGroup"));

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        java.util.Date dateFrom = null;
        java.util.Date dateTo = null;
        try {
            dateFrom = sdf.parse(periodFrom);
            dateTo = sdf.parse(periodTo);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<DiaryDoubleDTO> diaries = diaryService.getAverageBetweenPeriodByClassGroup(dateFrom, dateTo, classGroup);
        diaries.sort(Comparator.comparingLong(d -> d.getPupil().getId()));
        List<DiaryDTO> diariesDTO = diaries.stream().map(diary -> modelMapper.map(diary, DiaryDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(diariesDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/diaries/{date}/{pupil_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View a list of available Diary by pupil, date", response = DiaryDTO.class, responseContainer = "List")
    public ResponseEntity<List<DiaryDTO>> getAllDiaryByPupilAndDay(@PathVariable String date,
                                                                   @PathVariable Long pupil_id) {
        Pupil pupil = pupilService.findById(pupil_id).orElseThrow(() -> new ServiceNotFoundException(pupil_id, "pupil"));

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        java.util.Date currentDate = null;
        try {
            currentDate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<Diary> diaries = diaryService.findAllByPupilAndDay(pupil, currentDate);
        diaries.sort(Comparator.comparingLong(d -> d.getPupil().getId()));
        List<DiaryDTO> diariesDTO = diaries.stream().map(diary -> modelMapper.map(diary, DiaryDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(diariesDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/diary/{pupil_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View a list of available Diary by pupil", response = DiaryDTO.class, responseContainer = "List")
    public ResponseEntity<List<DiaryDTO>> getAllDiaryByPupil(@PathVariable Long pupil_id) {
        Pupil pupil = pupilService.findById(pupil_id).orElseThrow(() -> new ServiceNotFoundException(pupil_id, "pupil"));

        List<Diary> diaries = diaryService.findAllByPupil(pupil);
        diaries.sort(Comparator.comparingLong(d -> d.getPupil().getId()));
        List<DiaryDTO> diariesDTO = diaries.stream().map(diary -> modelMapper.map(diary, DiaryDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(diariesDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/diaries/{date}/{pupil_id}/{lesson_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View a list of available Diary by pupil, date, lesson", response = DiaryDTO.class, responseContainer = "List")
    public ResponseEntity<List<DiaryDTO>> getAllDiaryByPupilAndDayAndLesson(@PathVariable String date,
                                                                            @PathVariable Long pupil_id,
                                                                            @PathVariable Long lesson_id) {
        Pupil pupil = pupilService.findById(pupil_id).orElseThrow(() -> new ServiceNotFoundException(pupil_id, "pupil"));
        Lesson lesson = lessonService.findById(lesson_id).orElseThrow(() -> new ServiceNotFoundException(lesson_id, "lesson"));

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        java.util.Date currentDate = null;
        try {
            currentDate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<Diary> diaries = diaryService.findAllByPupilAndDayAndLesson(pupil, currentDate, lesson);
        diaries.sort(Comparator.comparingLong(d -> d.getPupil().getId()));
        List<DiaryDTO> diariesDTO = diaries.stream().map(diary -> modelMapper.map(diary, DiaryDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(diariesDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/diaries", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View a list of available Diary", response = DiaryDTO.class, responseContainer = "List")
    public ResponseEntity<List<DiaryDTO>> findAll() {
        List<Diary> diaries = diaryService.findAll();
        diaries.sort(Comparator.comparingLong(d -> d.getPupil().getId()));
        List<DiaryDTO> diariesDTO = diaries.stream().map(diary -> modelMapper.map(diary, DiaryDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(diariesDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/diaries", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Adding list new Diaries", response = DiaryDTO.class, responseContainer = "List")
    public ResponseEntity<List<DiaryDTO>> newDiaries(@RequestBody List<DiaryDTO> newDiaryDTOList) {

        List<Diary> diaries = newDiaryDTOList.stream().map(diary -> modelMapper.map(diary, Diary.class)).collect(Collectors.toList());
        for (Diary diary : diaries) {
            if (diary.getGrade() != 0) {
                diaryService.create(diary);
            } else if (diary.getId() != 0 && diary.getGrade() == 0) {
                diaryService.delete(diary.getId());
            }
        }
        List<DiaryDTO> diariesDTO = diaries.stream().map(diary -> modelMapper.map(diary, DiaryDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(diariesDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "/diaries/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View one available Diary", response = DiaryDTO.class)
    public ResponseEntity<DiaryDTO> findOne(@PathVariable Long id) {

        Diary diary = diaryService.findById(id)
                .orElseThrow(() -> new ServiceNotFoundException(id, "diary"));

        DiaryDTO diaryDTO = modelMapper.map(diary, DiaryDTO.class);
        return new ResponseEntity<>(diaryDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/diaries/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Replace one available Diary", response = DiaryDTO.class)
    public ResponseEntity<DiaryDTO> replaceDiary(@RequestBody DiaryDTO newDiaryDTO, @PathVariable Long id) {
        Diary diary = modelMapper.map(newDiaryDTO, Diary.class);
        diary.setId(id);
        diaryService.update(diary);

        DiaryDTO diaryDTO = modelMapper.map(diary, DiaryDTO.class);
        return new ResponseEntity<>(diaryDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/diaries/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete one available Diary", response = String.class)
    public ResponseEntity<String> deleteDiary(@PathVariable Long id) {
        diaryService.delete(id);
        return new ResponseEntity<String>("{\"info\": \"DELETE Response\"}", HttpStatus.OK);
    }
}
