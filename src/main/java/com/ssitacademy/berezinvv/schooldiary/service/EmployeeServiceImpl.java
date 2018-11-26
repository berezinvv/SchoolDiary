package com.ssitacademy.berezinvv.schooldiary.service;

import com.ssitacademy.berezinvv.schooldiary.exception.EntityNotFoundSchoolDiaryException;
import com.ssitacademy.berezinvv.schooldiary.model.Employee;
import com.ssitacademy.berezinvv.schooldiary.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee update(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundSchoolDiaryException(id, "employee"));
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public void delete(Employee employee) {
        employeeRepository.delete(employee);
    }

    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }
}
