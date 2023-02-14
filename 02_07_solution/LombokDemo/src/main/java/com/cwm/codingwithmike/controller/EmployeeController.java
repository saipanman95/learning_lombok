package com.cwm.codingwithmike.controller;

import com.cwm.codingwithmike.dto.EmployeeDto;
import com.cwm.codingwithmike.service.EmployeeService;
import lombok.Value;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Value
@RestController
@RequestMapping(path = {"/api/v1/employees"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDto>> listEmployees(){
        val employees = employeeService.findEmployees();
        if(employees.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(employees);
    }

    @GetMapping("/search/{lastName}/lastName")
    public ResponseEntity<List<EmployeeDto>> listEmployeesByLastName(@PathVariable("lastName")String lastName){
        val employeesByLastName = employeeService.findEmployeesByLastName(lastName);
        if(employeesByLastName.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(employeesByLastName);
    }

    @GetMapping("/search/{employeeId}/employeeId")
    public ResponseEntity<EmployeeDto> listEmployeesByLastName(@PathVariable("employeeId")Long employeeId){
        val employeeById = employeeService.findEmployeeById(employeeId);
        if(employeeById == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employeeById);
    }

    @PutMapping("/updateStatus/{employeeId}/employeeId/{employeeStatus}/employeeStatus")
    public ResponseEntity<EmployeeDto> updateEmployeeService(
            @PathVariable("employeeStatus") String employeeStatus,
            @PathVariable("employeeId") Long employeeId) {

        val employeeDto = employeeService.updateEmployeeStatus(employeeId, employeeStatus);
        if(null == employeeDto){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(employeeDto);
    }

    @PutMapping("/updateEmail/{employeeId}/employeeId/{employeeEmail}/employeeEmail")
    public ResponseEntity<EmployeeDto> updateEmployeeEmail(
            @PathVariable("employeeEmail") String employeeEmail,
            @PathVariable("employeeId") Long employeeId) {

        val employeeDto = employeeService.updateEmployeeEmail(employeeId, employeeEmail);
        if(null == employeeDto){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(employeeDto);
    }

}
