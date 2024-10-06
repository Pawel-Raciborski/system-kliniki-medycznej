package org.back.systemklinikimedycznej.doctor.validators;

import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import org.back.systemklinikimedycznej.doctor.exceptions.PwzNumberException;
import org.back.systemklinikimedycznej.doctor.repositories.DoctorRepository;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorValidator {
    private final DoctorRepository doctorRepository;
    public void validatePwzNumber(String pwzNumber) {
        Optional<Doctor> doctorOpt = doctorRepository.findByPwzNumber(pwzNumber);

        DoctorValidator.checkPwzNumberNotExist(doctorOpt.isPresent());
    }

    public static void checkPwzNumberNotExist(boolean existPwzNumber){
        if(existPwzNumber){
            throw new PwzNumberException("Podany numer pwz już zajęty!", HttpStatus.CONFLICT);
        }
    }
}
