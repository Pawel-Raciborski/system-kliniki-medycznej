package org.back.systemklinikimedycznej.address.validators;

import lombok.experimental.UtilityClass;
import org.back.systemklinikimedycznej.address.exceptions.AddressAlreadyExistsException;
import org.springframework.http.HttpStatus;

@UtilityClass
public class AddressValidator {
    public static void checkAddressNonExist(boolean existAddress){
        if(existAddress){
            throw new AddressAlreadyExistsException("Podany adres już istnieje!", HttpStatus.CONFLICT);
        }
    }
}
