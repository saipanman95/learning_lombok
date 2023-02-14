package com.cwm.codingwithmike;

import com.cwm.codingwithmike.dao.Address;
import com.cwm.codingwithmike.dao.Employee;
import com.cwm.codingwithmike.dao.EmployeeImage;
import com.cwm.codingwithmike.enums.EmployeeStatus;
import com.cwm.codingwithmike.repository.AddressRepository;
import com.cwm.codingwithmike.repository.EmployeeImageRepository;
import com.cwm.codingwithmike.repository.EmployeeRepository;
import lombok.val;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.io.ByteArrayInputStream;
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

			Employee.EmployeeBuilder builder = Employee.builder();
			Employee theodoreEmployee = builder
					.firstName("Theodore")
					.lastName("Roosevelt")
					.middleName("Cleveland")
					.suffix("Jr")
					.email("tcr@gmail.com")
					.createdDate(new Date())
					.build();

			Employee franklinEmployee = new Employee(null, "Franklin", "Benjamin", "Paul", "Sr", "bpr@gmail.com", null, null, EmployeeStatus.FULLTIME, new Date());
			LOGGER.info("Preparing to save employee objects");
			employeeRepository.save(theodoreEmployee);
			employeeRepository.save(franklinEmployee);

			Address franklinAddress = new Address(null,franklinEmployee, "home",
					"618033 Milk Street", null, "Boston", "MA", "02109", new Date());
			Address theodoreAddress = new Address(null, theodoreEmployee, "home",
					"28 East  20th Street", "Manhattan", "New York City", "NY", "10003", new Date());
			LOGGER.info("Preparing to save employee address objects");

			addressRepository.save(franklinAddress);
			addressRepository.save(theodoreAddress);

			EmployeeImage theodoreImage = new EmployeeImage(null, theodoreEmployee, rooseveltImg, new Date());
			EmployeeImage franklinImage = new EmployeeImage(null, franklinEmployee, franklinImg, new Date());

			employeeImageRepository.save(theodoreImage);
			employeeImageRepository.save(franklinImage);

		};
	}

	private byte[] retrieveStockImage(String imageName) throws IOException {
		LOGGER.info("LombokDemoApplication.retrieveStockImage() - attempting to retrieve the image");
		InputStream in = null;
		try {
			ClassPathResource classPathResource = new ClassPathResource(imageName);
			in = classPathResource.getInputStream();
			byte[] imageBytes = StreamUtils.copyToByteArray(in);
			LOGGER.info("LombokDemoApplication.retrieveStockImage() - byte[] length = " + imageBytes.length);

			return imageBytes;
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}
}

