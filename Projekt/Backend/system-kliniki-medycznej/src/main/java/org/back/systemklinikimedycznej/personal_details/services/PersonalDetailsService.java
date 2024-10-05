package org.back.systemklinikimedycznej.personal_details.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.address.repositories.entities.Address;
import org.back.systemklinikimedycznej.address.services.AddressService;
import org.back.systemklinikimedycznej.personal_details.validator.PersonalDetailsValidator;
import org.back.systemklinikimedycznej.personal_details.dto.PersonalDetailsDto;
import org.back.systemklinikimedycznej.personal_details.repositories.PersonalDetailsRepository;
import org.back.systemklinikimedycznej.personal_details.repositories.entities.PersonalDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonalDetailsService {
    private final PersonalDetailsRepository personalDetailsRepository;
    private final AddressService addressService;

    @Transactional
    public PersonalDetails create(PersonalDetailsDto personalDetailsDto) {
        Optional<PersonalDetails> foundPersonalDetailsOpt = personalDetailsRepository.findByPesel(personalDetailsDto.pesel());
        PersonalDetailsValidator.checkPeselNotExist(foundPersonalDetailsOpt.isPresent());

        foundPersonalDetailsOpt = personalDetailsRepository.findByPhoneNumber(personalDetailsDto.phoneNumber());
        PersonalDetailsValidator.checkPhoneNumberNotExist(foundPersonalDetailsOpt.isPresent());

        PersonalDetails personalDetailsToCreate = buildPersonalDetails(personalDetailsDto);
        return personalDetailsRepository.save(personalDetailsToCreate);
    }

    @Transactional
    public PersonalDetails update(PersonalDetailsDto newPersonalDetails) {
        Optional<PersonalDetails> personalDetailsOpt = personalDetailsRepository.findByPesel(newPersonalDetails.pesel());

        PersonalDetailsValidator.checkPeselExist(personalDetailsOpt.isEmpty());

        PersonalDetails personalDetailsToUpdate = personalDetailsOpt.get();
        Address updatedAddress = addressService.update(newPersonalDetails.address(), personalDetailsToUpdate.getAddress());

        setFields(newPersonalDetails, personalDetailsToUpdate, updatedAddress);

        return personalDetailsRepository.save(personalDetailsToUpdate);
    }

    private PersonalDetails buildPersonalDetails(PersonalDetailsDto personalDetailsDto) {
        Address createdAddress = addressService.create(personalDetailsDto.address());

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

    private void setFields(PersonalDetailsDto newPersonalDetails, PersonalDetails personalDetailsToUpdate, Address updatedAddress) {
        personalDetailsToUpdate.setAddress(updatedAddress);
        personalDetailsToUpdate.setName(newPersonalDetails.name());
        personalDetailsToUpdate.setSurname(newPersonalDetails.surname());
        personalDetailsToUpdate.setPesel(newPersonalDetails.pesel());
        personalDetailsToUpdate.setGender(newPersonalDetails.gender());
        personalDetailsToUpdate.setPhoneNumber(newPersonalDetails.phoneNumber());
        personalDetailsToUpdate.setBirthDate(newPersonalDetails.birthDate());
    }
}
