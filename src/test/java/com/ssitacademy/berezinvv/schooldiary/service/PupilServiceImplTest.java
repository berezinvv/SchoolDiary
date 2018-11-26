package com.ssitacademy.berezinvv.schooldiary.service;

import com.ssitacademy.berezinvv.schooldiary.model.ClassGroup;
import com.ssitacademy.berezinvv.schooldiary.model.Pupil;
import com.ssitacademy.berezinvv.schooldiary.repository.PupilRepository;
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
public class PupilServiceImplTest {

    private Pupil pupil_1;
    private Pupil pupil_2;
    private Pupil pupil_3;
    private ClassGroup classGroup;
    private List<Pupil> allPupils;

    @TestConfiguration
    static class PupilServiceImplTestContextConfiguration {
        @Bean
        public PupilService pupilService() {
            return new PupilServiceImpl();
        }
    }

    @Autowired
    private PupilService pupilService;

    @MockBean
    private PupilRepository pupilRepository;

    @Before
    public void setUp() {
        pupil_1 = new Pupil();
        pupil_1.setId(1L);
        pupil_2 = new Pupil();
        pupil_2.setId(2L);
        pupil_3 = new Pupil();
        pupil_3.setId(3L);
        classGroup = new ClassGroup(1L, "2-A");

        allPupils = Arrays.asList(pupil_3, pupil_2, pupil_1);

        Mockito.when(pupilRepository.save(pupil_1)).thenReturn(pupil_1);
        Mockito.when(pupilRepository.findById(14L)).thenReturn(null);
        Mockito.when(pupilRepository.findById(pupil_2.getId())).thenReturn(Optional.of(pupil_2));
        Mockito.when(pupilRepository.findAll()).thenReturn(allPupils);
        Mockito.when(pupilRepository.findAllPupilByClassGroup(classGroup)).thenReturn(allPupils);
    }

    @Test
    public void createTest() {
        Pupil pupil = pupilService.create(pupil_1);
        assertThat(pupil).isEqualTo(pupil_1);
    }

    @Test
    public void updateTest() {
        Pupil pupil = pupilService.update(pupil_1);
        assertThat(pupil).isEqualTo(pupil_1);
    }

    @Test
    public void findById_whenNonExistingIdTest() {
        Pupil pupil = pupilService.findById(14L);
        assertThat(pupil).isNull();
    }

    @Test
    public void testFindById_whenValidIdTest() {
        Pupil pupil = pupilService.findById(2L);
        assertThat(pupil).isEqualTo(pupil_2);
    }

    @Test
    public void findAllTest() {
        List<Pupil> allPupils = pupilService.findAll();
        assertThat(allPupils).hasSize(3).extracting(Pupil::getId).contains(pupil_1.getId(), pupil_2.getId(), pupil_3.getId());
    }

    @Test
    public void findAllPupilByClassGroupTest() {
        List<Pupil> allPupils = pupilService.findAllPupilByClassGroup(classGroup);
        assertThat(allPupils).hasSize(3).extracting(Pupil::getId).contains(pupil_1.getId(), pupil_2.getId(), pupil_3.getId());
    }
}