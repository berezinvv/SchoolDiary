package com.ssitacademy.berezinvv.schooldiary.service;

import com.ssitacademy.berezinvv.schooldiary.model.ClassGroup;
import com.ssitacademy.berezinvv.schooldiary.model.Pupil;
import com.ssitacademy.berezinvv.schooldiary.repository.ClassGroupRepository;
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
public class ClassGroupServiceImplTest {

    private ClassGroup class3A;
    private ClassGroup class2B;
    private ClassGroup class1A;
    private Pupil pupil;
    private List<ClassGroup> allClassGroup;

    @TestConfiguration
    static class ClassGroupServiceImplTestContextConfiguration {
        @Bean
        public ClassGroupService classGroupService() {
            return new ClassGroupServiceImpl();
        }
    }

    @Autowired
    private ClassGroupService classGroupService;

    @MockBean
    private ClassGroupRepository classGroupRepository;

    @Before
    public void setUp() {
        class3A = new ClassGroup(1L,"3-A");
        class2B = new ClassGroup(2L,"2-B");
        class1A = new ClassGroup(3L,"1-A");
        pupil = new Pupil(1L,"firstName","lastName");
        allClassGroup = Arrays.asList(class3A, class2B, class1A);

        Mockito.when(classGroupRepository.save(class3A)).thenReturn(class3A);
        Mockito.when(classGroupRepository.findById(4L)).thenReturn(null);
        Mockito.when(classGroupRepository.findById(class2B.getId())).thenReturn(Optional.of(class2B));
        Mockito.when(classGroupRepository.findAll()).thenReturn(allClassGroup);
        Mockito.when(classGroupRepository.findAllClassGroupByPupil(pupil)).thenReturn(allClassGroup);
    }

    @Test
    public void createTest() {
        ClassGroup classGroup = classGroupService.create(class3A);
        assertThat(classGroup).isEqualTo(class3A);
    }

    @Test
    public void updateTest() {
        ClassGroup classGroup = classGroupService.update(class3A);
        assertThat(classGroup).isEqualTo(class3A);
    }

    @Test
    public void findById_whenNonExistingIdTest() {
            Optional<ClassGroup> classGroup = classGroupService.findById(4L);
            assertThat(classGroup).isNull();
    }

    @Test
    public void findById_whenValidIdTest() {
        Optional<ClassGroup> classGroup = classGroupService.findById(2L);
        assertThat(classGroup.get()).isEqualTo(class2B);
    }

    @Test
    public void findAllTest() {
        List<ClassGroup> allClassGroup = classGroupService.findAll();
        assertThat(allClassGroup).hasSize(3).extracting(ClassGroup::getName).contains(class3A.getName(), class2B.getName(), class1A.getName());
    }

    @Test
    public void findAllClassGroupByPupilTest() {
        List<ClassGroup> allClassGroup = classGroupService.findAllClassGroupByPupil(pupil);
        assertThat(allClassGroup).hasSize(3).extracting(ClassGroup::getName).contains(class3A.getName(), class2B.getName(), class1A.getName());
    }
}
