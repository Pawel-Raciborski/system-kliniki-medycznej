package org.back.systemklinikimedycznej.patient.validators;

import lombok.extern.slf4j.Slf4j;
import org.back.systemklinikimedycznej.patient.exceptions.PatientException;
import org.back.systemklinikimedycznej.patient.repositories.entities.Patient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

@Slf4j
@Component
public class PatientValidator {

    public void validateHasParentPeselIfPatientIsNotAnAdult(Patient patientToValidate){
        LocalDate birthDate = patientToValidate.getPersonalDetails().getBirthDate();
        LocalDate currentDate = LocalDate.now();

        log.info("Checking patient with birth date {} is adult",birthDate);
        Period period = Period.between(birthDate,currentDate);

        if(period.getYears() >= 18){
            log.info("Patient is adult");
            return;
        }
        log.warn("Patient is not adult");

        String parentPesel = patientToValidate.getParentPesel();
        if(Objects.isNull(parentPesel) || parentPesel.isBlank()){
            log.error("Patient is not adult and parent pesel was not passed");
            throw new PatientException("Pacjent jest niepełnoletni, nie podano peselu rodzica!", HttpStatus.CONFLICT);
        }
        log.info("Patient is not adult, but contains required parent pesel");
    };
}
