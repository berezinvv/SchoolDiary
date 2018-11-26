package com.ssitacademy.berezinvv.schooldiary.service;

import com.ssitacademy.berezinvv.schooldiary.model.ClassGroup;
import com.ssitacademy.berezinvv.schooldiary.model.Diary;
import com.ssitacademy.berezinvv.schooldiary.model.Lesson;
import com.ssitacademy.berezinvv.schooldiary.model.Pupil;
import com.ssitacademy.berezinvv.schooldiary.repository.DiaryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
public class DiaryServiceImplTest {

    private Diary diary_1;
    private Diary diary_2;
    private Diary diary_3;
    private ClassGroup classgroup;
    private Pupil pupil;
    private Date date;
    private Lesson lesson;

    private List<Diary> allDiaries;

    @TestConfiguration
    static class DiaryServiceImplTestContextConfiguration {
        @Bean
        public DiaryService diaryService() {
            return new DiaryServiceImpl();
        }
    }

    @Autowired
    private DiaryService diaryService;

    @MockBean
    private DiaryRepository diaryRepository;

    @Before
    public void setUp() {
        diary_1 = new Diary();
        diary_1.setId(1L);
        diary_2 = new Diary();
        diary_2.setId(2L);
        diary_3 = new Diary();
        diary_3.setId(3L);
        classgroup = new ClassGroup(1L, "3-A");
        pupil = new Pupil(1L, "firstName", "lastName");
        date = new Date(System.currentTimeMillis());
        lesson = new Lesson(1L, "English");

        allDiaries = Arrays.asList(diary_3, diary_2, diary_1);

        Mockito.when(diaryRepository.save(diary_1)).thenReturn(diary_1);
        Mockito.when(diaryRepository.findById(14L)).thenReturn(null);
        Mockito.when(diaryRepository.findById(diary_2.getId())).thenReturn(Optional.of(diary_2));
        Mockito.when(diaryRepository.findAll()).thenReturn(allDiaries);
        Mockito.when(diaryRepository.findAllByClassGroup(classgroup)).thenReturn(allDiaries);
        Mockito.when(diaryRepository.findAllByPupilAndDay(pupil, date)).thenReturn(allDiaries);
        Mockito.when(diaryRepository.findAllByPupilAndDayAndLesson(pupil, date, lesson)).thenReturn(allDiaries);
    }

    @Test
    public void createTest() {
        Diary diary = diaryService.create(diary_1);
        assertThat(diary).isEqualTo(diary_1);
    }

    @Test
    public void updateTest() {
        Diary diary = diaryService.update(diary_1);
        assertThat(diary).isEqualTo(diary_1);
    }

    @Test
    public void findById_whenNonExistingIdTest() {
        Diary diary = diaryService.findById(14L);
        assertThat(diary).isNull();
    }

    @Test
    public void testFindById_whenValidIdTest() {
        Diary diary = diaryService.findById(2L);
        assertThat(diary).isEqualTo(diary_2);
    }

    @Test
    public void findAllTest() {
        List<Diary> allDiaries = diaryService.findAll();
        assertThat(allDiaries).hasSize(3).extracting(Diary::getId).contains(diary_1.getId(), diary_2.getId(), diary_3.getId());
    }

    @Test
    public void findAllByClassGroupTest() {
        List<Diary> allDiaries = diaryService.findAllByClassGroup(classgroup);
        assertThat(allDiaries).hasSize(3).extracting(Diary::getId).contains(diary_1.getId(), diary_2.getId(), diary_3.getId());
    }

    @Test
    public void findAllByPupilAndDayTest() {
        List<Diary> allDiaries = diaryService.findAllByPupilAndDay(pupil, date);
        assertThat(allDiaries).hasSize(3).extracting(Diary::getId).contains(diary_1.getId(), diary_2.getId(), diary_3.getId());
    }

    @Test
    public void findAllByPupilAndDayAndLessonTest() {
        List<Diary> allDiaries = diaryService.findAllByPupilAndDayAndLesson(pupil, date, lesson);
        assertThat(allDiaries).hasSize(3).extracting(Diary::getId).contains(diary_1.getId(), diary_2.getId(), diary_3.getId());
    }
}