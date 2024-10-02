package org.back.systemklinikimedycznej.address.dto;

public record AddressForm(
        String street,
        String apartmentNumber,
        String postalCode,
        String city
) {
}
