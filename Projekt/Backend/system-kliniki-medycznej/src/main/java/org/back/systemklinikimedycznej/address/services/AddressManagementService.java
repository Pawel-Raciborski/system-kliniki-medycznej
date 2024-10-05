package org.back.systemklinikimedycznej.address.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.address.dto.AddressForm;
import org.back.systemklinikimedycznej.address.repositories.AddressRepository;
import org.back.systemklinikimedycznej.address.repositories.entities.Address;
import org.back.systemklinikimedycznej.address.repositories.entities.City;
import org.back.systemklinikimedycznej.address.validators.AddressValidator;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressManagementService {
    private final AddressRepository addressRepository;
    public void validateAddressExist(Address addressToCreate) {
        Example<Address> addressExample = Example.of(addressToCreate);

        AddressValidator.checkAddressNonExist(addressRepository.findOne(addressExample).isPresent());
    }

    public void setAddressFields(AddressForm newAddress, Address oldAddress, City city) {
        oldAddress.setStreet(newAddress.street());
        oldAddress.setApartmentNumber(newAddress.apartmentNumber());
        oldAddress.setPostalCode(newAddress.postalCode());
        oldAddress.setCity(city);
    }
}
