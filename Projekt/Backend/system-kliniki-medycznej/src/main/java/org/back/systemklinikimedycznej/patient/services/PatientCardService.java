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
import java.nio.file.Paths;
import java.util.Optional;

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

    @Transactional
    public void deleteCardForPatient(Patient patientToRemove) {
        PatientCard patientCard = findPatientCardWithPesel(patientToRemove.getPersonalDetails().getPesel());

        Path fileToRemove = Paths.get(patientCard.getPatientDataFilesPath());
        patientFileService.deleteFile(fileToRemove);

        patientCardRepository.delete(patientCard);
    }

    public PatientCard findPatientCardWithPesel(String pesel) {
        return patientCardRepository.findCardForPatientWithPesel(pesel)
                .orElseThrow(() -> new PatientCardException("Nie znaleziono karty dla pacjena o peselu %s".formatted(pesel),HttpStatus.NOT_FOUND));
    }
}
