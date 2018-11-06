package com.ssitacademy.berezinvv.schooldiary.controller.api;

import com.ssitacademy.berezinvv.schooldiary.dto.DiaryDTO;
import com.ssitacademy.berezinvv.schooldiary.exception.ServiceNotFoundException;
import com.ssitacademy.berezinvv.schooldiary.model.Diary;
import com.ssitacademy.berezinvv.schooldiary.service.DiaryService;
import io.swagger.annotations.ApiOperation;
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
public class DiaryController {

    @Autowired
    DiaryService diaryService;

    ModelMapper modelMapper = new ModelMapper();

    @GetMapping(value = "/diaries", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View a list of available Diary", response = Iterable.class)
    public ResponseEntity<List<DiaryDTO>> findAll() {
        List<DiaryDTO> diarysDTO = new ArrayList<>();
        List<Diary> diarys = diaryService.findAll();

        diarysDTO = diarys.stream().map(diary -> modelMapper.map(diary, DiaryDTO.class)).collect(Collectors.toList());
        for (Diary diary : diarys) {
            DiaryDTO diaryDTO = modelMapper.map(diary, DiaryDTO.class);
            diarysDTO.add(diaryDTO);
        }
        return new ResponseEntity<>(diarysDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/diaries", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Adding new Diary", response = Iterable.class)
    public ResponseEntity<DiaryDTO> newDiary(@RequestBody DiaryDTO newDiaryDTO) {
        Diary diary = modelMapper.map(newDiaryDTO, Diary.class);
        diaryService.create(diary);
        DiaryDTO diaryDTO = modelMapper.map(diary, DiaryDTO.class);
        return new ResponseEntity<>(diaryDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/diaries/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View one available Diary", response = Iterable.class)
    public ResponseEntity<DiaryDTO> findOne(@PathVariable Long id) {

        Diary diary = diaryService.findById(id)
                .orElseThrow(() -> new ServiceNotFoundException(id, "diary"));

        DiaryDTO diaryDTO = modelMapper.map(diary, DiaryDTO.class);
        return new ResponseEntity<>(diaryDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/diaries/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Replace one available Diary", response = Iterable.class)
    public ResponseEntity<DiaryDTO> replaceDiary(@RequestBody DiaryDTO newDiaryDTO, @PathVariable Long id) {
        Diary diary = modelMapper.map(newDiaryDTO, Diary.class);
        diary.setId(id);
        diaryService.create(diary);

        DiaryDTO diaryDTO = modelMapper.map(diary, DiaryDTO.class);
        return new ResponseEntity<>(diaryDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/diaries/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete one available Diary", response = Iterable.class)
    public ResponseEntity<String> deleteDiary(@PathVariable Long id) {
        diaryService.delete(id);
        return new ResponseEntity<String>("{\"info\": \"DELETE Response\"}", HttpStatus.OK);
    }
}
