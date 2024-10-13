package org.back.systemklinikimedycznej.patient.validators;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.back.systemklinikimedycznej.patient.exceptions.PatientException;
import org.back.systemklinikimedycznej.patient.repositories.PatientCardRepository;
import org.back.systemklinikimedycznej.patient.repositories.entities.Patient;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_card.PatientCard;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class PatientCardValidator {
    private final PatientCardRepository patientCardRepository;

    public void validatePatientCardNotExistForPatient(Patient patient) {
        String pesel = patient.getPersonalDetails().getPesel();
        log.info("Start validation, checking if patient card not exist for patient with pesel: [{}]", pesel);
        Optional<PatientCard> optionalPatientCard = patientCardRepository.findCardForPatientWithPesel(pesel);

        checkNotExistPatientCard(optionalPatientCard.isPresent());
        log.info("Patient validation finished successfully");
    }

    private void checkNotExistPatientCard(boolean existPatientCard) {
        if(existPatientCard){
            log.error("Error occurred, patient card already exist");
            throw new PatientException("Dla podanego użytkownika karta pacjenta już istnieje!", HttpStatus.CONFLICT);
        }
    }
}
