package com.ssitacademy.berezinvv.schooldiary.service;

import com.ssitacademy.berezinvv.schooldiary.model.ClassGroup;
import com.ssitacademy.berezinvv.schooldiary.model.Lesson;
import com.ssitacademy.berezinvv.schooldiary.repository.LessonRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
public class LessonServiceImplTest {

    private Lesson lesson_1;
    private Lesson lesson_2;
    private Lesson lesson_3;
    private List<Lesson> allLessons;

    @TestConfiguration
    static class LessonServiceImplTestContextConfiguration {
        @Bean
        public LessonService lessonService() {
            return new LessonServiceImpl();
        }
    }

    @Autowired
    private LessonService lessonService;

    @MockBean
    private LessonRepository lessonRepository;

    @Before
    public void setUp() {
        lesson_1 = new Lesson(1L, "English");
        lesson_2 = new Lesson(2L, "Maths");
        lesson_3 = new Lesson(3L, "French");

        allLessons = Arrays.asList(lesson_3, lesson_2, lesson_1);

        Mockito.when(lessonRepository.save(lesson_1)).thenReturn(lesson_1);
        Mockito.when(lessonRepository.findById(14L)).thenReturn(null);
        Mockito.when(lessonRepository.findById(lesson_2.getId())).thenReturn(Optional.of(lesson_2));
        Mockito.when(lessonRepository.findAll()).thenReturn(allLessons);
    }

    @Test
    public void createTest() {
        Lesson lesson = lessonService.create(lesson_1);
        assertThat(lesson).isEqualTo(lesson_1);
    }

    @Test
    public void updateTest() {
        Lesson lesson = lessonService.update(lesson_1);
        assertThat(lesson).isEqualTo(lesson_1);
    }

    @Test
    public void findById_whenNonExistingIdTest() {
        Lesson lesson = lessonService.findById(14L);
        assertThat(lesson).isNull();
    }

    @Test
    public void testFindById_whenValidIdTest() {
        Lesson lesson = lessonService.findById(2L);
        assertThat(lesson).isEqualTo(lesson_2);
    }

    @Test
    public void findAllTest() {
        List<Lesson> allLessons = lessonService.findAll();
        assertThat(allLessons).hasSize(3).extracting(Lesson::getName).contains(lesson_1.getName(), lesson_2.getName(), lesson_3.getName());
    }
}