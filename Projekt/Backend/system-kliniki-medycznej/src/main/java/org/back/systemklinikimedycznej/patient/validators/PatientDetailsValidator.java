package org.back.systemklinikimedycznej.patient.validators;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.patient.exceptions.PersonalDetailsException;
import org.back.systemklinikimedycznej.patient.repositories.PatientDetailsRepository;
import org.back.systemklinikimedycznej.patient.repositories.entities.PatientDetails;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_card.PatientCard;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PatientDetailsValidator {
    private final PatientDetailsRepository patientDetailsRepository;

    public void validatePersonalDetailsNotExistForPatientCard(PatientCard patientCard) {
        Optional<PatientDetails> optionalPatientDetails = patientDetailsRepository.findByPatientCard(patientCard);

        checkNotExistPatientDetails(optionalPatientDetails.isPresent());
    }

    private void checkNotExistPatientDetails(boolean personalDetailsExist) {
        if(personalDetailsExist){
            throw new PersonalDetailsException("Dane dla podanego pacjenta już istnieją!", HttpStatus.CONFLICT);
        }
    }
}
