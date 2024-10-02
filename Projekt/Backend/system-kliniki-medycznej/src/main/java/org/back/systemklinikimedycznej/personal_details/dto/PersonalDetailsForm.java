package org.back.systemklinikimedycznej.personal_details.dto;

import org.back.systemklinikimedycznej.address.dto.AddressForm;

import java.time.LocalDate;

public record PersonalDetailsForm(
        String pesel,
        String name,
        String surname,
        LocalDate birthDate,
        String gender,
        String phoneNumber,
        AddressForm address
) {
}
