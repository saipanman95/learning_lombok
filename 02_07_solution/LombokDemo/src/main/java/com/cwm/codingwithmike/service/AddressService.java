package com.cwm.codingwithmike.service;

import com.cwm.codingwithmike.dao.Address;
import com.cwm.codingwithmike.dto.AddressDto;
import com.cwm.codingwithmike.repository.AddressRepository;
import lombok.Value;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Value
@Service
public class AddressService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddressService.class);

    private final AddressRepository addressRepository;

    public List<AddressDto> findAddresses(){
        LOGGER.info("AddressService.findAddresses() - retrieving all addresses");
        val allAddresses = addressRepository.findAll();

        return addressListConverter(allAddresses);
    }

    public List<AddressDto> findAddressesByCity(String city){
        LOGGER.info("AddressService.findAddressesByCity(...) - retrieving all addresses by city");
        val cityAddresses = addressRepository.findByCity(city);
        return addressListConverter(cityAddresses);
    }

    public List<AddressDto> findAddressesByState(String state){
        LOGGER.info("AddressService.findAddressesByState(...) - retrieving all addresses by state");
        val stateAddresses = addressRepository.findByState(state);

        return addressListConverter(stateAddresses);
    }

    private List<AddressDto> addressListConverter(List<Address> addresses) {
        LOGGER.info("AddressService.addressConverter - converting Address Entity to Address DTO");
        val addressDtos = new ArrayList<AddressDto>();
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
