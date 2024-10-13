package org.back.systemklinikimedycznej.patient.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.back.systemklinikimedycznej.patient.exceptions.PatientCardException;
import org.back.systemklinikimedycznej.patient.repositories.PatientCardRepository;
import org.back.systemklinikimedycznej.patient.repositories.entities.Patient;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_card.PatientCard;
import org.back.systemklinikimedycznej.patient.util.PatientCardManagerUtil;
import org.back.systemklinikimedycznej.patient.validators.PatientCardValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Path;

@Slf4j
@Service
@RequiredArgsConstructor
public class PatientCardService {
    private final PatientCardRepository patientCardRepository;
    private final PatientCardValidator patientCardValidator;
    private final PatientFileService patientFileService;
    public PatientCard findCardForPatientWithEmail(String patientEmail) {
        return patientCardRepository.findCardForPatientWithEmail(patientEmail)
                .orElseThrow(() -> new PatientCardException("Nie znaleziono karty pacjenta dla podanego użytkownika", HttpStatus.NOT_FOUND));
    }

    @Transactional
    public PatientCard createCardForPatient(Patient patient) {
        log.info("Start processing patient card creation");
        Path patientFilesPath = patientFileService.createDirectoryForPatient(patient);
        PatientCard initialPatientCard = PatientCardManagerUtil.buildPatientCard(patient,patientFilesPath);

        patientCardValidator.validatePatientCardNotExistForPatient(patient);

        return patientCardRepository.save(initialPatientCard);
    }
}
