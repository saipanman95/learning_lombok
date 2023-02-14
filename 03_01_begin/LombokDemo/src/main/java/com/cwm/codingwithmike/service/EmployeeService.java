package com.cwm.codingwithmike.service;

import com.cwm.codingwithmike.dao.Address;
import com.cwm.codingwithmike.dao.Employee;
import com.cwm.codingwithmike.dto.AddressDto;
import com.cwm.codingwithmike.dto.EmployeeDto;
import com.cwm.codingwithmike.enums.EmployeeStatus;
import com.cwm.codingwithmike.repository.EmployeeImageRepository;
import com.cwm.codingwithmike.repository.EmployeeRepository;
import lombok.Value;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Value
@Service
public class EmployeeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeRepository employeeRepository;
    private final EmployeeImageRepository employeeImageRepository;

    public List<EmployeeDto> findEmployees() {
        LOGGER.info("EmployeeService.findEmployees() - retrieving all employees");
        val employees = employeeRepository.findAll();
        return employeeListConverter(employees);
    }

    public List<EmployeeDto> findEmployeesByLastName(String lastName) {
        LOGGER.info("EmployeeService.findEmployeesByLastName() - retrieving all employees with lastName of {lastName}", lastName);
        val employees = employeeRepository.findByLastName(lastName);
        return employeeListConverter(employees);
    }

    public EmployeeDto findEmployeeById(Long id) {
        LOGGER.info("EmployeeService.findEmployeeById(...) - retrieving employee with id of {id}", id);
        val employee = employeeRepository.findById(id);

        if (employee.isPresent()) {
            Employee emp = employee.get();
            return employeeConverter(emp);
        } else {
            return null;
        }
    }

    public EmployeeDto updateEmployeeStatus(Long employeeId, String employeeStatus) {
        LOGGER.info("EmployeeService.updateEmployeeStatus(...) - retrieving employee with employeeId of {employeeId} and updating with status {employeeStatus}", employeeId, employeeStatus);

        val employeeDtoById = findEmployeeById(employeeId);
        val employeeDto = employeeDtoById.withEmployeeStatus(
                EmployeeStatus.convert(employeeStatus)
        );
        return updateEmployee(employeeDto);
    }

    public EmployeeDto updateEmployeeEmail(Long employeeId, String employeeEmail) {
        LOGGER.info("EmployeeService.updateEmployeeEmail(...) - retrieving employee with employeeId of {employeeId} and updating with email {employeeEmail}", employeeId, employeeEmail);

        val employeeDtoById = findEmployeeById(employeeId);
        val employeeDto = employeeDtoById.withEmail(employeeEmail);
        return updateEmployee(employeeDto);
    }
    public EmployeeDto updateEmployee(final EmployeeDto employeeDto) {
        LOGGER.info("EmployeeService.updateEmployee(...) - {employeeDto}", employeeDto);

        val employeeImage = employeeImageRepository.findByEmployeeId(employeeDto.id());
        Employee.EmployeeBuilder builder = Employee.builder();
        val emp = builder
                .id(employeeDto.id())
                .lastName(employeeDto.lastName())
                .firstName(employeeDto.firstName())
                .middleName(employeeDto.middleName())
                .suffix(employeeDto.suffix())
                .email(employeeDto.email())
                .employeeStatus(employeeDto.employeeStatus())
                .build();

        emp.setAddresses(addressDtoListToAddressList(
                employeeDto.addresses(),emp)
        );

        Employee savedEmployee = employeeRepository.save(emp);
        return employeeConverter(savedEmployee);
    }

    private List<EmployeeDto> employeeListConverter(List<Employee> employees) {
        LOGGER.info("EmployeeService.employeeConverter - converting Employee Entity to Employee DTO");
        val employeeDtos = new ArrayList<EmployeeDto>();

        for (Employee emp : employees) {
            employeeDtos.add(
                    employeeConverter(emp)
            );
        }
        return employeeDtos;
    }

    private List<AddressDto> addressListConverter(List<Address> addresses) {
        LOGGER.info("EmployeeService.addressConverter - converting Address Entity to Address DTO");
        val addressDtos = new ArrayList<AddressDto>();
        for (Address addy : addresses) {
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

    private EmployeeDto employeeConverter(Employee employee) {
        val employeeDto = new EmployeeDto(
                employee.getId(),
                employee.getLastName(),
                employee.getFirstName(),
                employee.getMiddleName(),
                employee.getSuffix(),
                employee.getEmail(),
                addressListConverter(employee.getAddresses()),
                employee.getEmployeeStatus(),
                employee.getCreatedDate()
        );

        System.out.println(employeeDto);
        System.out.println(employeeDto.hashCode());
        System.out.println(employeeDto.equals(employeeDto));
        System.out.println(employeeDto.firstName());
        return employeeDto;
    }

    private List<Address> addressDtoListToAddressList(List<AddressDto> addressDtos, Employee employee) {
        List<Address> addresses = new ArrayList<>();
        for (AddressDto addDto : addressDtos) {
            addresses.add(
                    new Address(
                            addDto.id(),
                            employee,
                            addDto.addressType(),
                            addDto.addressLine1(),
                            addDto.addressLine2(),
                            addDto.city(),
                            addDto.state(),
                            addDto.zipCode(),
                            addDto.createdDate()
                    )
            );
        }
        return addresses;
    }


}
