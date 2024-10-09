package org.back.systemklinikimedycznej.personal_details.validator;

import lombok.RequiredArgsConstructor;
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
public class PersonalDetailsValidator {
    private final PersonalDetailsRepository personalDetailsRepository;

    @Transactional
    public void validatePeselAndPhoneNumber(PersonalDetailsDto personalDetailsDto) {
        validatePeselNotExist(personalDetailsDto.pesel());
        validatePhoneNotExist(personalDetailsDto.phoneNumber());
    }

    private void validatePeselNotExist(String pesel) {
        Optional<PersonalDetails> foundPersonalDetailsOpt = personalDetailsRepository.findByPesel(pesel);
        checkPeselNotExist(foundPersonalDetailsOpt.isPresent());
    }

    private void validatePhoneNotExist(String phoneNumber) {
        Optional<PersonalDetails> foundPersonalDetailsOpt = personalDetailsRepository.findByPhoneNumber(phoneNumber);
        checkPhoneNumberNotExist(foundPersonalDetailsOpt.isPresent());
    }

    private void checkPeselNotExist(boolean peselExist) {
        if (peselExist) {
            throw new PeselExistsException("Błędny pesel", HttpStatus.CONFLICT);
        }
    }

    private void checkPhoneNumberNotExist(boolean phoneExist) {
        if (phoneExist) {
            throw new PhoneExistsException("Numer telefonu jest zajęty!", HttpStatus.CONFLICT);
        }
    }
}
