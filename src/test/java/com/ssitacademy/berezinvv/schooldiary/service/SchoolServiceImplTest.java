package com.ssitacademy.berezinvv.schooldiary.service;

import com.ssitacademy.berezinvv.schooldiary.model.School;
import com.ssitacademy.berezinvv.schooldiary.repository.SchoolRepository;
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
public class SchoolServiceImplTest {

    private School school_1;
    private School school_2;
    private School school_3;
    private List<School> allSchools;

    @TestConfiguration
    static class SchoolServiceImplTestContextConfiguration {
        @Bean
        public SchoolService schoolService() {
            return new SchoolServiceImpl();
        }
    }

    @Autowired
    private SchoolService schoolService;

    @MockBean
    private SchoolRepository schoolRepository;

    @Before
    public void setUp() {
        school_1 = new School(1L,"№1");
        school_2 = new School(2L,"№2");
        school_3 = new School(3L,"№3");
        allSchools = Arrays.asList(school_3, school_2, school_1);

        Mockito.when(schoolRepository.save(school_1)).thenReturn(school_1);
        Mockito.when(schoolRepository.findById(14L)).thenReturn(null);
        Mockito.when(schoolRepository.findById(2L)).thenReturn(Optional.of(school_2));
        Mockito.when(schoolRepository.findAll()).thenReturn(allSchools);
    }

    @Test
    public void createTest() {
        School school = schoolService.create(school_1);
        assertThat(school).isEqualTo(school_1);
    }

    @Test
    public void updateTest() {
        School school = schoolService.update(school_1);
        assertThat(school).isEqualTo(school_1);
    }

    @Test
    public void findById_whenNonExistingIdTest() {
        Optional<School> school = schoolService.findById(14L);
        assertThat(school).isNull();
    }

    @Test
    public void testFindById_whenValidIdTest() {
        Optional<School> school = schoolService.findById(2L);
        assertThat(school.get()).isEqualTo(school_2);
    }

    @Test
    public void findAllTest() {
        List<School> allSchools = schoolService.findAll();
        assertThat(allSchools).hasSize(3).extracting(School::getName).contains(school_1.getName(), school_2.getName(), school_3.getName());
    }
}