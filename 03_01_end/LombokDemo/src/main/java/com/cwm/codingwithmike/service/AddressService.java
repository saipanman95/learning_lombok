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
import java.util.Optional;

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

    public AddressDto findAddressById(Long addressId){
        val idAddress = addressRepository.findById(addressId);
        if(idAddress.isPresent()){
            Address address = idAddress.get();
            return addressConverter(address);
        } else {
            return null;
        }
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
                    addressConverter(addy)
            );
        }
        return addressDtos;
    }

    private AddressDto addressConverter(Address address){
        return new AddressDto(
                address.getId(),
                address.getAddressType(),
                address.getAddressLine1(),
                address.getAddressLine2(),
                address.getCity(),
                address.getState(),
                address.getZipCode(),
                address.getCreatedDate()
        );
    }
}
