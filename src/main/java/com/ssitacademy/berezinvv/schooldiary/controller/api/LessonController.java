package com.ssitacademy.berezinvv.schooldiary.controller.api;

import com.ssitacademy.berezinvv.schooldiary.dto.LessonDTO;
import com.ssitacademy.berezinvv.schooldiary.model.Lesson;
import com.ssitacademy.berezinvv.schooldiary.service.LessonService;
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
public class LessonController {

    @Autowired
    private LessonService lessonService;

    private ModelMapper modelMapper = new ModelMapper();

    @GetMapping(value = "/lessons", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View a list of available Lesson", response = LessonDTO.class, responseContainer = "List")
    public ResponseEntity<List<LessonDTO>> findAll() {
        List<Lesson> lessons = lessonService.findAll();

        List<LessonDTO> lessonsDTO = lessons.stream().map(lesson -> modelMapper.map(lesson, LessonDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(lessonsDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/lessons")
    @ApiOperation(value = "Adding new Lesson", response = LessonDTO.class)
    ResponseEntity<LessonDTO> newLesson(@RequestBody LessonDTO newLessonDTO) {
        Lesson lesson = modelMapper.map(newLessonDTO, Lesson.class);
        lessonService.create(lesson);
        LessonDTO lessonDTO = modelMapper.map(lesson, LessonDTO.class);
        return new ResponseEntity<>(lessonDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "/lessons/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View one available Lesson", response = LessonDTO.class)
    ResponseEntity<LessonDTO> findOne(@PathVariable Long id) {
        Lesson lesson = lessonService.findById(id);

        LessonDTO lessonDTO = modelMapper.map(lesson, LessonDTO.class);
        return new ResponseEntity<>(lessonDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/lessons/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Replace one available Lesson", response = LessonDTO.class)
    ResponseEntity<LessonDTO> replaceLesson(@RequestBody LessonDTO newLessonDTO, @PathVariable Long id) {
        Lesson lesson = modelMapper.map(newLessonDTO, Lesson.class);
        lesson.setId(id);
        lessonService.update(lesson);

        LessonDTO lessonDTO = modelMapper.map(lesson, LessonDTO.class);

        return new ResponseEntity<>(lessonDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/lessons/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete one available Lesson", response = String.class)
    ResponseEntity<String> deleteLesson(@PathVariable Long id) {
        lessonService.delete(id);
        return new ResponseEntity<String>("{\"info\": \"DELETE Response\"}", HttpStatus.OK);
    }
}
