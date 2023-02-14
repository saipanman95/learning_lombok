package com.cwm.codingwithmike.controller;

import com.cwm.codingwithmike.dao.Employee;
import com.cwm.codingwithmike.dto.EmployeeDto;
import com.cwm.codingwithmike.dto.RestContainer;
import com.cwm.codingwithmike.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/rest/employee/all")
    public RestContainer<?> listEmployees(){
        return employeeService.findEmployees();
    }

    @GetMapping("/rest/employee/search/{lastName}")
    public RestContainer<?> listEmployeesByLastName(@PathVariable("lastName")String lastName){
        return employeeService.findEmployeesByLastName(lastName);
    }

    @GetMapping("/rest/employee/get/{employeeId}")
    public RestContainer<?> listEmployeesByLastName(@PathVariable("employeeId")Long employeeId){
        return employeeService.findEmployeeById(employeeId);
    }

}
