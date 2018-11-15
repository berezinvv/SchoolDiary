package com.ssitacademy.berezinvv.schooldiary.controller.api;

import com.ssitacademy.berezinvv.schooldiary.dto.EmployeeDTO;
import com.ssitacademy.berezinvv.schooldiary.exception.EntityNotFoundSchoolDiaryException;
import com.ssitacademy.berezinvv.schooldiary.model.Employee;
import com.ssitacademy.berezinvv.schooldiary.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    private ModelMapper modelMapper = new ModelMapper();

    @GetMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View a list of available Employee", response = EmployeeDTO.class, responseContainer="List")
    public ResponseEntity<List<EmployeeDTO>> findAll() {
        List<Employee> employees = employeeService.findAll();

        List<EmployeeDTO> employeesDTO = employees.stream().map(employee->modelMapper.map(employee, EmployeeDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(employeesDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Adding new Employee", response = EmployeeDTO.class)
    public ResponseEntity<EmployeeDTO> newEmployee(@RequestBody EmployeeDTO newEmployeeDTO) {

        Employee employee = modelMapper.map(newEmployeeDTO, Employee.class);
        employeeService.create(employee);
        EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
        return new ResponseEntity<>(employeeDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "/employees/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View one available Employee", response = EmployeeDTO.class)
    public ResponseEntity<EmployeeDTO> findOne(@PathVariable Long id) {

        Employee employee = employeeService.findById(id)
                .orElseThrow(() -> new EntityNotFoundSchoolDiaryException(id, "employee"));

        EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/employees/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Replace one available Employee", response = EmployeeDTO.class)
    public ResponseEntity<EmployeeDTO> replaceEmployee(@RequestBody EmployeeDTO newEmployeeDTO, @PathVariable Long id) {

        Employee employee = modelMapper.map(newEmployeeDTO, Employee.class);
        employee.setId(id);
        employeeService.update(employee);

        EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/employees/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete one available Employee", response = String.class)
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        employeeService.delete(id);
        return new ResponseEntity<String>("{\"info\": \"DELETE Response\"}", HttpStatus.OK);
    }
}
