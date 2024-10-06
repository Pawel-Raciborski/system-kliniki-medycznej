package org.back.systemklinikimedycznej.address.util;

import lombok.experimental.UtilityClass;
import org.back.systemklinikimedycznej.address.dto.AddressForm;
import org.back.systemklinikimedycznej.address.repositories.entities.Address;
import org.back.systemklinikimedycznej.address.repositories.entities.City;

@UtilityClass
public class AddressManagerUtil {

    public static void setAddressFields(AddressForm newAddress, Address oldAddress, City city) {
        oldAddress.setStreet(newAddress.street());
        oldAddress.setApartmentNumber(newAddress.apartmentNumber());
        oldAddress.setPostalCode(newAddress.postalCode());
        oldAddress.setCity(city);
    }

    public static Address buildAddressFromFormAndWithCity(AddressForm addressForm, City addressCity) {
        return Address.builder()
                .apartmentNumber(addressForm.apartmentNumber())
                .street(addressForm.street())
                .apartmentNumber(addressForm.apartmentNumber())
                .postalCode(addressForm.postalCode())
                .city(addressCity)
                .build();
    }
}
