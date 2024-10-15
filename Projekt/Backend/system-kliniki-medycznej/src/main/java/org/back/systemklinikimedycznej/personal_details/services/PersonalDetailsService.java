package org.back.systemklinikimedycznej.personal_details.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.address.repositories.entities.Address;
import org.back.systemklinikimedycznej.address.services.AddressService;
import org.back.systemklinikimedycznej.exceptions.GlobalAppException;
import org.back.systemklinikimedycznej.patient.exceptions.PersonalDetailsException;
import org.back.systemklinikimedycznej.personal_details.util.PersonalDetailsManagerUtil;
import org.back.systemklinikimedycznej.personal_details.validator.PersonalDetailsValidator;
import org.back.systemklinikimedycznej.personal_details.dto.PersonalDetailsDto;
import org.back.systemklinikimedycznej.personal_details.repositories.PersonalDetailsRepository;
import org.back.systemklinikimedycznej.personal_details.repositories.entities.PersonalDetails;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PersonalDetailsService {
    private final PersonalDetailsRepository personalDetailsRepository;
    private final PersonalDetailsValidator personalDetailsValidator;
    private final AddressService addressService;

    /**
     * <h4>Creates person personal details.</h4>
     *
     * <p>This method creates personal details</p>
     *
     * @param personalDetailsDto data with personal details
     * @return Created personal details
     * */
    @Transactional
    public PersonalDetails create(PersonalDetailsDto personalDetailsDto) {
        personalDetailsValidator.validatePeselAndPhoneNumber(personalDetailsDto.pesel(),personalDetailsDto.phoneNumber());

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

    public void deletePersonalDetails(PersonalDetails personalDetailsToRemove) {
        addressService.deleteAddressIfNecessary(personalDetailsToRemove.getAddress());
        personalDetailsRepository.delete(personalDetailsToRemove);
    }

    public PersonalDetails findByPhoneNumber(String phoneNumber){
        return personalDetailsRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new PersonalDetailsException("Nie znaleziono danych z podanym numerem telefonu!",HttpStatus.NOT_FOUND));
    }
}
