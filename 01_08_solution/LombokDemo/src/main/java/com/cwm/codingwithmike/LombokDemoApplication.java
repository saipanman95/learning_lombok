package com.cwm.codingwithmike;

import com.cwm.codingwithmike.dao.Address;
import com.cwm.codingwithmike.dao.Employee;
import com.cwm.codingwithmike.repository.AddressRepository;
import com.cwm.codingwithmike.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
@EnableAutoConfiguration
public class LombokDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(LombokDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner mappingDemo(EmployeeRepository employeeRepository,
										 AddressRepository addressRepository){
		return args -> {
			Employee theodoreEmployee = new Employee(null, "Roosevelt", "Theodore", "Cleveland", "Jr",null,  new Date());
			Employee franklinEmployee = new Employee(null, "Franklin", "Benjamin", "Paul", "Sr", null, new Date());
			employeeRepository.save(theodoreEmployee);
			employeeRepository.save(franklinEmployee);

			Address franklinAddress = new Address(null,franklinEmployee, "home",
					"618033 Milk Street", null, "Boston", "MA", "02109", new Date());
			Address theodoreAddress = new Address(null, theodoreEmployee, "home",
					"28 East  20th Street", "Manahattan", "New York City", "NY", "10003", new Date());

			addressRepository.save(franklinAddress);
			addressRepository.save(theodoreAddress);

		};
	}
}

