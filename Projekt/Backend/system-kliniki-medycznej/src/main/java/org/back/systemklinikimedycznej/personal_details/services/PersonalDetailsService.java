package org.back.systemklinikimedycznej.personal_details.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.address.repositories.entities.Address;
import org.back.systemklinikimedycznej.address.services.AddressService;
import org.back.systemklinikimedycznej.exceptions.GlobalAppException;
import org.back.systemklinikimedycznej.personal_details.util.PersonalDetailsManagerUtil;
import org.back.systemklinikimedycznej.personal_details.validator.PersonalDetailsValidator;
import org.back.systemklinikimedycznej.personal_details.dto.PersonalDetailsDto;
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
    private final PersonalDetailsValidator personalDetailsValidator;
    private final AddressService addressService;

    @Transactional
    public PersonalDetails create(PersonalDetailsDto personalDetailsDto) {
        personalDetailsValidator.validatePeselAndPhoneNumber(personalDetailsDto);

        Address createdAddress = addressService.create(personalDetailsDto.address());
        PersonalDetails personalDetailsToCreate = PersonalDetailsManagerUtil.buildPersonalDetailsWithAddress(personalDetailsDto,createdAddress);
        return personalDetailsRepository.save(personalDetailsToCreate);
    }

    @Transactional
    public PersonalDetails update(PersonalDetailsDto newPersonalDetails) {
        PersonalDetails personalDetailsToUpdate = getByPesel(newPersonalDetails);
        Address updatedAddress = addressService.update(newPersonalDetails.address(), personalDetailsToUpdate.getAddress());

        PersonalDetailsManagerUtil.setFields(newPersonalDetails, personalDetailsToUpdate, updatedAddress);

        return personalDetailsRepository.save(personalDetailsToUpdate);
    }

    private PersonalDetails getByPesel(PersonalDetailsDto newPersonalDetails) {
        return personalDetailsRepository.findByPesel(newPersonalDetails.pesel())
                .orElseThrow(() -> new GlobalAppException("Nie znaleziono danych z podanym PESELEM", HttpStatus.NOT_FOUND));
    }

}
