package org.back.systemklinikimedycznej.personal_details.util;

import lombok.experimental.UtilityClass;
import org.back.systemklinikimedycznej.address.repositories.entities.Address;
import org.back.systemklinikimedycznej.personal_details.dto.PersonalDetailsDto;
import org.back.systemklinikimedycznej.personal_details.repositories.entities.PersonalDetails;

@UtilityClass
public class PersonalDetailsManagerUtil {
    public static PersonalDetails buildPersonalDetailsWithAddress(PersonalDetailsDto personalDetailsDto, Address createdAddress) {
        return PersonalDetails.builder()
                .pesel(personalDetailsDto.pesel())
                .name(personalDetailsDto.name())
                .surname(personalDetailsDto.surname())
                .gender(personalDetailsDto.gender())
                .birthDate(personalDetailsDto.birthDate())
                .phoneNumber(personalDetailsDto.phoneNumber())
                .address(createdAddress)
                .build();
    }

    public static void setFields(PersonalDetailsDto newPersonalDetails, PersonalDetails personalDetailsToUpdate, Address updatedAddress) {
        personalDetailsToUpdate.setAddress(updatedAddress);
        personalDetailsToUpdate.setName(newPersonalDetails.name());
        personalDetailsToUpdate.setSurname(newPersonalDetails.surname());
        personalDetailsToUpdate.setPesel(newPersonalDetails.pesel());
        personalDetailsToUpdate.setGender(newPersonalDetails.gender());
        personalDetailsToUpdate.setPhoneNumber(newPersonalDetails.phoneNumber());
        personalDetailsToUpdate.setBirthDate(newPersonalDetails.birthDate());
    }
}
