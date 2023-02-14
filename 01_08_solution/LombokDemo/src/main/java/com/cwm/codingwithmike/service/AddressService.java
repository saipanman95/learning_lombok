package com.cwm.codingwithmike.service;

import com.cwm.codingwithmike.dao.Address;
import com.cwm.codingwithmike.dto.AddressDto;
import com.cwm.codingwithmike.dto.RestContainer;
import com.cwm.codingwithmike.repository.AddressRepository;
import lombok.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Value
@Service
public class AddressService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddressService.class);

    private final AddressRepository addressRepository;

    public RestContainer<?> findAddresses(){
        LOGGER.info("AddressService.findAddresses() - retrieving all addresses");
        final List<Address> allAddresses = addressRepository.findAll();

        if(allAddresses.isEmpty()){
            return new RestContainer<>("No Records Found", "Address");
        } else {
            return new RestContainer<>(addressListConverter(allAddresses), "Address");
        }
    }

    public RestContainer<?> findAddressesByCity(String city){
        LOGGER.info("AddressService.findAddressesByCity(...) - retrieving all addresses by city");
        final List<Address> cityAddresses = addressRepository.findByCity(city);

        if(cityAddresses.isEmpty()){
            return new RestContainer<>("No Records Found", "Address");
        } else {
            return new RestContainer<>(addressListConverter(cityAddresses), "Address");
        }
    }

    public RestContainer<?> findAddressesByState(String state){
        LOGGER.info("AddressService.findAddressesByState(...) - retrieving all addresses by state");
        final List<Address> stateAddresses = addressRepository.findByState(state);

        if(stateAddresses.isEmpty()){
            return new RestContainer<>("No Records Found", "Address");
        } else {
            return new RestContainer<>(addressListConverter(stateAddresses), "Address");
        }
    }

    private List<AddressDto> addressListConverter(List<Address> addresses) {
        LOGGER.info("AddressService.addressConverter - converting Address Entity to Address DTO");
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
}
