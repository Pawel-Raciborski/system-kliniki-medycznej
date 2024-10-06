package org.back.systemklinikimedycznej.address.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.address.dto.AddressForm;
import org.back.systemklinikimedycznej.address.repositories.AddressRepository;
import org.back.systemklinikimedycznej.address.repositories.entities.Address;
import org.back.systemklinikimedycznej.address.repositories.entities.City;
import org.back.systemklinikimedycznej.address.util.AddressManagerUtil;
import org.back.systemklinikimedycznej.address.validators.AddressValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final CityService cityService;
    private final AddressValidator addressValidator;

    @Transactional
    public Address create(AddressForm addressForm){
        Address addressToCreate = buildAddressFromForm(addressForm);
        addressValidator.validateAddressExist(addressToCreate);

        return addressRepository.save(addressToCreate);
    }

    private Address buildAddressFromForm(AddressForm addressForm) {
        City addressCity = cityService.findByName(addressForm.city());

        return AddressManagerUtil.buildAddressFromFormAndWithCity(addressForm, addressCity);
    }

    public Address update(AddressForm newAddress, Address oldAddress) {
        City city = cityService.findByName(newAddress.city());
        AddressManagerUtil.setAddressFields(newAddress, oldAddress, city);

        return addressRepository.save(oldAddress);
    }
}
