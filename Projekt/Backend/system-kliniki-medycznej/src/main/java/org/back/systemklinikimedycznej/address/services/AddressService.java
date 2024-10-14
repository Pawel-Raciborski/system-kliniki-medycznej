package org.back.systemklinikimedycznej.address.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.address.dto.AddressForm;
import org.back.systemklinikimedycznej.address.repositories.AddressRepository;
import org.back.systemklinikimedycznej.address.repositories.entities.Address;
import org.back.systemklinikimedycznej.address.repositories.entities.City;
import org.back.systemklinikimedycznej.address.util.AddressManagerUtil;
import org.back.systemklinikimedycznej.address.validators.AddressValidator;
import org.back.systemklinikimedycznej.patient.repositories.PatientDetailsRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final CityService cityService;
    private final PatientDetailsRepository patientDetailsRepository;

    @Transactional
    public Address create(AddressForm addressForm){
        Address addressToCreate = buildAddressFromForm(addressForm);

        Example<Address> addressExample = Example.of(addressToCreate);
        Optional<Address> optionalAddress = addressRepository.findOne(addressExample);

        return optionalAddress.orElseGet(() -> addressRepository.save(addressToCreate));


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

    public void deleteAddressIfNecessary(Address address) {
        Long numberOfReferencesToAddress = patientDetailsRepository.countRefencedAddress(address.getId());

        if(numberOfReferencesToAddress == 0){
            addressRepository.delete(address);
        }
    }
}
