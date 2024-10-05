package org.back.systemklinikimedycznej.address.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.address.dto.AddressForm;
import org.back.systemklinikimedycznej.address.exceptions.AddressAlreadyExistsException;
import org.back.systemklinikimedycznej.address.repositories.AddressRepository;
import org.back.systemklinikimedycznej.address.repositories.CityRepository;
import org.back.systemklinikimedycznej.address.repositories.entities.Address;
import org.back.systemklinikimedycznej.address.repositories.entities.City;
import org.back.systemklinikimedycznej.address.validators.AddressValidator;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final AddressManagementService addressManagementService;
    private final CityService cityService;
    @Transactional
    public Address create(AddressForm addressForm){
        Address addressToCreate = buildAddressFromForm(addressForm);
        addressManagementService.validateAddressExist(addressToCreate);

        return addressRepository.save(addressToCreate);
    }

    private Address buildAddressFromForm(AddressForm addressForm) {
        City addressCity = cityService.findByName(addressForm.city());

        return Address.builder()
                .apartmentNumber(addressForm.apartmentNumber())
                .street(addressForm.street())
                .apartmentNumber(addressForm.apartmentNumber())
                .postalCode(addressForm.postalCode())
                .city(addressCity)
                .build();
    }

    public Address update(AddressForm newAddress, Address oldAddress) {
        City city = cityService.findByName(newAddress.city());
        addressManagementService.setAddressFields(newAddress, oldAddress, city);

        return addressRepository.save(oldAddress);
    }
}
