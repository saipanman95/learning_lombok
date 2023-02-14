package com.cwm.codingwithmike;

import com.cwm.codingwithmike.dao.Address;
import com.cwm.codingwithmike.dao.Employee;
import com.cwm.codingwithmike.dao.EmployeeImage;
import com.cwm.codingwithmike.repository.AddressRepository;
import com.cwm.codingwithmike.repository.EmployeeImageRepository;
import com.cwm.codingwithmike.repository.EmployeeRepository;
import lombok.Cleanup;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

@SpringBootApplication
@EnableAutoConfiguration
public class LombokDemoApplication {
	public static final Logger LOGGER = LoggerFactory.getLogger(LombokDemoApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(LombokDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner mappingDemo(EmployeeRepository employeeRepository,
										 AddressRepository addressRepository,
										 EmployeeImageRepository employeeImageRepository){
		return args -> {
			byte[] franklinImg = retrieveStockImage("benjamin_franklin.png");
			byte[] rooseveltImg = retrieveStockImage("theo_roosevelt.png");

			val builder = Employee.builder();
			val theodoreEmployee = builder
					.firstName("Theodore")
					.lastName("Roosevelt")
					.middleName("Cleveland")
					.suffix("Jr")
					.email("tcr@gmail.com")
					.build();

			val franklinEmployee = builder
					.firstName("Benjamin")
					.lastName("Franklin")
					.middleName("Paul")
					.suffix("Sr")
					.email("bpr@gmail.com")
					.build();

			LOGGER.info("Preparing to save employee objects");
			employeeRepository.save(theodoreEmployee);
			employeeRepository.save(franklinEmployee);

			val addBuilder = Address.builder();
			val franklinAddress = addBuilder
					.employee(franklinEmployee)
					.addressLine1("618033 Milk Street")
					.city("Boston")
					.state("MA")
					.zipCode("02109")
					.build();

			val theodoreAddress = addBuilder
					.employee(theodoreEmployee)
					.addressLine1("28 East  20th Street")
					.addressLine2("Manhattan")
					.city("New York City")
					.state("NY")
					.zipCode("10003")
					.build();
			LOGGER.info("Preparing to save employee address objects");

			addressRepository.save(franklinAddress);
			addressRepository.save(theodoreAddress);

			val theodoreImage = new EmployeeImage(null, theodoreEmployee, rooseveltImg, new Date());
			val franklinImage = new EmployeeImage(null, franklinEmployee, franklinImg, new Date());

			employeeImageRepository.save(theodoreImage);
			employeeImageRepository.save(franklinImage);

			readFile();
		};
	}

	private byte[] retrieveStockImage(String imageName) throws IOException {
		LOGGER.info("LombokDemoApplication.retrieveStockImage() - attempting to retrieve the image");
		ClassPathResource classPathResource = new ClassPathResource(imageName);
		@Cleanup InputStream in = classPathResource.getInputStream();

		byte[] imageBytes = StreamUtils.copyToByteArray(in);
		LOGGER.info("LombokDemoApplication.retrieveStockImage() - byte[] length = " + imageBytes.length);

		return imageBytes;

	}


	private void readFile() throws IOException {
		InputStream in = null;

		try {
			in = new FileInputStream("../../../learning_lombok/examples/text.info");
			int content;
			LOGGER.info("LombokDemoApplication.readFile() - preparing to read the file.");
			while ((content = in.read()) != -1) {
				System.out.print((char)content);
			}
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}
}

