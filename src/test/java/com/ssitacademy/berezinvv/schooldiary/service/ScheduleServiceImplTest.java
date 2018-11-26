package com.ssitacademy.berezinvv.schooldiary.service;

import com.ssitacademy.berezinvv.schooldiary.model.Schedule;
import com.ssitacademy.berezinvv.schooldiary.repository.ScheduleRepository;
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
public class ScheduleServiceImplTest {

    private Schedule schedule_1;
    private Schedule schedule_2;
    private Schedule schedule_3;
    private List<Schedule> allSchedules;

    @TestConfiguration
    static class ScheduleServiceImplTestContextConfiguration {
        @Bean
        public ScheduleService scheduleService() {
            return new ScheduleServiceImpl();
        }
    }

    @Autowired
    private ScheduleService scheduleService;

    @MockBean
    private ScheduleRepository scheduleRepository;

    @Before
    public void setUp() {
        schedule_1 = new Schedule();
        schedule_1.setId(1L);
        schedule_2 = new Schedule();
        schedule_2.setId(2L);
        schedule_3 = new Schedule();
        schedule_3.setId(3L);
        allSchedules = Arrays.asList(schedule_3, schedule_2, schedule_1);

        Mockito.when(scheduleRepository.save(schedule_1)).thenReturn(schedule_1);
        Mockito.when(scheduleRepository.findById(schedule_2.getId())).thenReturn(Optional.of(schedule_2));
        Mockito.when(scheduleRepository.findAll()).thenReturn(allSchedules);
    }

    @Test
    public void createTest() {
        Schedule schedule = scheduleService.create(schedule_1);
        assertThat(schedule).isEqualTo(schedule_1);
    }

    @Test
    public void updateTest() {
        Schedule schedule = scheduleService.update(schedule_1);
        assertThat(schedule).isEqualTo(schedule_1);
    }

    @Test
    public void testFindById_whenValidIdTest() {
        Schedule schedule = scheduleService.findById(2L);
        assertThat(schedule).isEqualTo(schedule_2);
    }

    @Test
    public void findAllTest() {
        List<Schedule> allSchedules = scheduleService.findAll();
        assertThat(allSchedules).hasSize(3).extracting(Schedule::getId).contains(schedule_1.getId(), schedule_2.getId(), schedule_3.getId());
    }
}