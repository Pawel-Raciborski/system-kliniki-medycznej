package org.back.systemklinikimedycznej.personal_details.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.address.repositories.entities.Address;
import org.back.systemklinikimedycznej.address.services.AddressService;
import org.back.systemklinikimedycznej.personal_details.dto.PersonalDetailsForm;
import org.back.systemklinikimedycznej.personal_details.exceptions.PeselExistsException;
import org.back.systemklinikimedycznej.personal_details.exceptions.PhoneExistsException;
import org.back.systemklinikimedycznej.personal_details.repositories.PersonalDetailsRepository;
import org.back.systemklinikimedycznej.personal_details.repositories.entities.PersonalDetails;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonalDetailsService {
    private final PersonalDetailsRepository personalDetailsRepository;
    private final AddressService addressService;

    @Transactional
    public PersonalDetails create(PersonalDetailsForm personalDetailsForm) {
        Optional<PersonalDetails> foundPersonalDetailsOpt = personalDetailsRepository.findByPesel(personalDetailsForm.pesel());

        if(foundPersonalDetailsOpt.isPresent()){
            throw new PeselExistsException("Błędny pesel", HttpStatus.CONFLICT);
        }

        foundPersonalDetailsOpt = personalDetailsRepository.findByPhoneNumber(personalDetailsForm.phoneNumber());

        if(foundPersonalDetailsOpt.isPresent()){
            throw new PhoneExistsException("Numer telefonu jest zajęty!", HttpStatus.CONFLICT);
        }

        PersonalDetails personalDetailsToCreate = buildPersonalDetails(personalDetailsForm);

        return personalDetailsRepository.save(personalDetailsToCreate);
    }

    private PersonalDetails buildPersonalDetails(PersonalDetailsForm personalDetailsForm) {
        Address createdAddress = addressService.create(personalDetailsForm.address());

        return PersonalDetails.builder()
                .pesel(personalDetailsForm.pesel())
                .name(personalDetailsForm.name())
                .surname(personalDetailsForm.surname())
                .gender(personalDetailsForm.gender())
                .birthDate(personalDetailsForm.birthDate())
                .phoneNumber(personalDetailsForm.phoneNumber())
                .address(createdAddress)
                .build();
    }
}
