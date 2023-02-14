package com.cwm.codingwithmike.service;

import com.cwm.codingwithmike.dao.Address;
import com.cwm.codingwithmike.dao.Employee;
import com.cwm.codingwithmike.dto.AddressDto;
import com.cwm.codingwithmike.dto.EmployeeDto;
import com.cwm.codingwithmike.dto.RestContainer;
import com.cwm.codingwithmike.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class EmployeeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeRepository employeeRepository;

    public RestContainer<?> findEmployees(){
        LOGGER.info("EmployeeService.findEmployees() - retrieving all employees");
        final List<Employee> employees = employeeRepository.findAll();
        if(employees.isEmpty()){
            return new RestContainer<>("No Records Found", "Employee");
        } else {
            return new RestContainer<>(employeeListConverter(employees), "Employee");
        }
    }

    public RestContainer<?> findEmployeesByLastName(String lastName){
        LOGGER.info("EmployeeService.findEmployeesByLastName() - retrieving all employees with lastName of {lastName}", lastName);
        final List<Employee> employees = employeeRepository.findByLastName(lastName);
        if(employees.isEmpty()){
            return new RestContainer<>("No Employees Found", "Employee");
        } else {
            return new RestContainer<>(employeeListConverter(employees), "Employee");
        }
    }

    public RestContainer<?> findEmployeeById(Long id){
        LOGGER.info("EmployeeService.findEmployeeById(...) - retrieving employee with id of {id}", id);
        final Optional<Employee> employee = employeeRepository.findById(id);

        if(employee.isPresent()){
            Employee emp = employee.get();
            return new RestContainer<>(
                    employeeConverter(emp), "employee"
            );
        } else {
            return new RestContainer<>("No Record Found", "Employee");
        }
    }

    private List<EmployeeDto> employeeListConverter(List<Employee> employees){
        LOGGER.info("EmployeeService.employeeConverter - converting Employee Entity to Employee DTO");
        List<EmployeeDto> employeeDtos = new ArrayList<>();

        for(Employee emp : employees){
            employeeDtos.add(
                    employeeConverter(emp)
            );
        }
        return employeeDtos;
    }

    private List<AddressDto> addressListConverter(List<Address> addresses) {
        LOGGER.info("EmployeeService.addressConverter - converting Address Entity to Address DTO");
        List<AddressDto> addressDtos = new ArrayList<>();
        for(Address addy : addresses){
            addressDtos.add(
                    new AddressDto(
                            addy.getId(),
                            addy.getAddressType(),
                            addy.getAddressLine1(),
                            addy.getAddressLine2(),
                            addy.getCity(),
                            addy.getState(),
                            addy.getZipCode(),
                            addy.getCreatedDate()
                    )
            );
        }
        return addressDtos;
    }

    private EmployeeDto employeeConverter(Employee employee){
       EmployeeDto employeeDto =  new EmployeeDto(
               employee.getId(),
               employee.getLastName(),
               employee.getFirstName(),
               employee.getMiddleName(),
               employee.getSuffix(),
               addressListConverter(employee.getAddresses()),
               employee.getCreatedDate()
       );
        System.out.println(employeeDto.toString());
        System.out.println(employeeDto.hashCode());
        System.out.println(employeeDto.firstName());
        return employeeDto;
    }
}
