package com.ssitacademy.berezinvv.schooldiary.service;

import com.ssitacademy.berezinvv.schooldiary.model.Employee;
import com.ssitacademy.berezinvv.schooldiary.repository.EmployeeRepository;
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
public class EmployeeServiceImplTest {

    private Employee employee_1;
    private Employee employee_2;
    private Employee employee_3;
    private List<Employee> allEmployees;

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
        @Bean
        public EmployeeService employeeService() {
            return new EmployeeServiceImpl();
        }
    }

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Before
    public void setUp() {
        employee_1 = new Employee();
        employee_1.setId(1L);
        employee_2 = new Employee();
        employee_2.setId(2L);
        employee_3 = new Employee();
        employee_3.setId(3L);

        allEmployees = Arrays.asList(employee_3, employee_2, employee_1);

        Mockito.when(employeeRepository.save(employee_1)).thenReturn(employee_1);
        Mockito.when(employeeRepository.findById(14L)).thenReturn(null);
        Mockito.when(employeeRepository.findById(employee_2.getId())).thenReturn(Optional.of(employee_2));
        Mockito.when(employeeRepository.findAll()).thenReturn(allEmployees);
    }

    @Test
    public void createTest() {
        Employee employee = employeeService.create(employee_1);
        assertThat(employee).isEqualTo(employee_1);
    }

    @Test
    public void updateTest() {
        Employee employee = employeeService.update(employee_1);
        assertThat(employee).isEqualTo(employee_1);
    }

    @Test
    public void findById_whenNonExistingIdTest() {
        Optional<Employee> employee = employeeService.findById(14L);
        assertThat(employee).isNull();
    }

    @Test
    public void testFindById_whenValidIdTest() {
        Optional<Employee> employee = employeeService.findById(2L);
        assertThat(employee.get()).isEqualTo(employee_2);
    }

    @Test
    public void findAllTest() {
        List<Employee> allEmployees = employeeService.findAll();
        assertThat(allEmployees).hasSize(3).extracting(Employee::getId).contains(employee_1.getId(), employee_2.getId(), employee_3.getId());
    }
}