package org.back.systemklinikimedycznej.doctor.validators;

import lombok.experimental.UtilityClass;
import org.back.systemklinikimedycznej.doctor.exceptions.DoctorSpecializationExistException;
import org.springframework.http.HttpStatus;

@UtilityClass
public class DoctorSpecializationValidator {
    public static void checkDoctorHasNotSpecialization(boolean hasSpecialization, String specializationName){
        if(hasSpecialization){
            throw new DoctorSpecializationExistException("Lekarz posiada już specjalizację o nazwie [%s]!".formatted(specializationName), HttpStatus.CONFLICT);
        }
    }

}
