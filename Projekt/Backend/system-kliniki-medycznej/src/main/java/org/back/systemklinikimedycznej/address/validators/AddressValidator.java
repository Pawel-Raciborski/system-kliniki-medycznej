package org.back.systemklinikimedycznej.address.validators;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.address.exceptions.AddressAlreadyExistsException;
import org.back.systemklinikimedycznej.address.repositories.AddressRepository;
import org.back.systemklinikimedycznej.address.repositories.entities.Address;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressValidator {

    private final AddressRepository addressRepository;
    public void validateAddressExist(Address addressToCreate) {
        Example<Address> addressExample = Example.of(addressToCreate);

        checkAddressNonExist(addressRepository.findOne(addressExample).isPresent());
    }

    public void checkAddressNonExist(boolean existAddress){
        if(existAddress){
            throw new AddressAlreadyExistsException("Podany adres już istnieje!", HttpStatus.CONFLICT);
        }
    }
}
