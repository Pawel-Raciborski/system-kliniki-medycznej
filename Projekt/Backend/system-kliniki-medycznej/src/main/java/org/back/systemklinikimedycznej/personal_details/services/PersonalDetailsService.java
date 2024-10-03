package org.back.systemklinikimedycznej.personal_details.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.address.repositories.entities.Address;
import org.back.systemklinikimedycznej.address.services.AddressService;
import org.back.systemklinikimedycznej.exceptions.GlobalAppException;
import org.back.systemklinikimedycznej.personal_details.dto.PersonalDetailsDto;
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
    public PersonalDetails create(PersonalDetailsDto personalDetailsDto) {
        Optional<PersonalDetails> foundPersonalDetailsOpt = personalDetailsRepository.findByPesel(personalDetailsDto.pesel());

        if (foundPersonalDetailsOpt.isPresent()) {
            throw new PeselExistsException("Błędny pesel", HttpStatus.CONFLICT);
        }

        foundPersonalDetailsOpt = personalDetailsRepository.findByPhoneNumber(personalDetailsDto.phoneNumber());

        if (foundPersonalDetailsOpt.isPresent()) {
            throw new PhoneExistsException("Numer telefonu jest zajęty!", HttpStatus.CONFLICT);
        }

        PersonalDetails personalDetailsToCreate = buildPersonalDetails(personalDetailsDto);

        return personalDetailsRepository.save(personalDetailsToCreate);
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

    @Transactional
    public PersonalDetails update(PersonalDetailsDto newPersonalDetails) {
        Optional<PersonalDetails> personalDetailsOpt = personalDetailsRepository.findByPesel(newPersonalDetails.pesel());

        if (personalDetailsOpt.isEmpty()) {
            throw new GlobalAppException("Nie znaleziono danych z podanym PESELEM", HttpStatus.CONFLICT);
        }

        PersonalDetails personalDetailsToUpdate = personalDetailsOpt.get();
        Address updatedAddress = addressService.update(newPersonalDetails.address(), personalDetailsToUpdate.getAddress());

        setFields(newPersonalDetails, personalDetailsToUpdate, updatedAddress);

        return personalDetailsRepository.save(personalDetailsToUpdate);
    }

    private static void setFields(PersonalDetailsDto newPersonalDetails, PersonalDetails personalDetailsToUpdate, Address updatedAddress) {
        personalDetailsToUpdate.setAddress(updatedAddress);
        personalDetailsToUpdate.setName(newPersonalDetails.name());
        personalDetailsToUpdate.setSurname(newPersonalDetails.surname());
        personalDetailsToUpdate.setPesel(newPersonalDetails.pesel());
        personalDetailsToUpdate.setGender(newPersonalDetails.gender());
        personalDetailsToUpdate.setPhoneNumber(newPersonalDetails.phoneNumber());
        personalDetailsToUpdate.setBirthDate(newPersonalDetails.birthDate());
    }
}
