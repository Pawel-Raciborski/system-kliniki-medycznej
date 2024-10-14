package org.back.systemklinikimedycznej.personal_details.validator;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.personal_details.dto.PersonalDetailsDto;
import org.back.systemklinikimedycznej.personal_details.exceptions.PeselExistsException;
import org.back.systemklinikimedycznej.personal_details.exceptions.PhoneExistsException;
import org.back.systemklinikimedycznej.personal_details.repositories.PersonalDetailsRepository;
import org.back.systemklinikimedycznej.personal_details.repositories.entities.PersonalDetails;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonalDetailsValidator {
    private final PersonalDetailsRepository personalDetailsRepository;

    @Transactional
    public void validatePeselAndPhoneNumber(String pesel, String phoneNumber) {
        if(Objects.nonNull(pesel)){
            validatePeselNotExist(pesel);
        }
        if(Objects.nonNull(phoneNumber)){
            validatePhoneNotExist(phoneNumber);
        }
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
            throw new PeselExistsException("Podany pesel jest już zajęty", HttpStatus.CONFLICT);
        }
    }

    private void checkPhoneNumberNotExist(boolean phoneExist) {
        if (phoneExist) {
            throw new PhoneExistsException("Numer telefonu jest zajęty!", HttpStatus.CONFLICT);
        }
    }
}
