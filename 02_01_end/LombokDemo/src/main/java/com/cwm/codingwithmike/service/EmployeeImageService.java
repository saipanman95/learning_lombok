package com.cwm.codingwithmike.service;

import com.cwm.codingwithmike.dao.EmployeeImage;
import com.cwm.codingwithmike.repository.EmployeeImageRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeImageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeImageService.class);
    private final EmployeeImageRepository employeeImageRepository;

    public byte[] findEmployeeImageById(final Long id){
        LOGGER.info("EmployeeImageService.findEmployeeImageById() - retrieving employee image");
        final EmployeeImage  employeeImage = employeeImageRepository.findByEmployeeId(id);
        return (null != employeeImage) ? employeeImage.getImage() : null;
    }

    public List<byte[]> findAllEmployeeImages(){
        LOGGER.info("EmployeeImageService.findAllEmployeeImages() - retrieving all employee images");
        final List<EmployeeImage> all = employeeImageRepository.findAll();
        List<byte[]> images = new ArrayList<>();
        for(EmployeeImage employeeImage : all){
            images.add(employeeImage.getImage());
        }
        return images;
    }
}
