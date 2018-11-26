package com.ssitacademy.berezinvv.schooldiary.service;


import com.ssitacademy.berezinvv.schooldiary.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee create(Employee employee);

    Employee update(Employee employee);

    Employee findById(Long id);

    List<Employee> findAll();

    void delete(Employee employee);

    void delete(Long id);

}
