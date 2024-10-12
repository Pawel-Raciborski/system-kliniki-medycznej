package org.back.systemklinikimedycznej.patient.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.patient.exceptions.PatientCardException;
import org.back.systemklinikimedycznej.patient.repositories.PatientCardRepository;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_card.PatientCard;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientCardService {
    private final PatientCardRepository patientCardRepository;
    public PatientCard findCardForPatientWithEmail(String patientEmail) {
        return patientCardRepository.findCardForPatientWithEmail(patientEmail)
                .orElseThrow(() -> new PatientCardException("Nie znaleziono karty pacjenta dla podanego użytkownika", HttpStatus.NOT_FOUND));
    }
}
