package org.back.systemklinikimedycznej.patient.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.patient.controllers.dto.PatientDetailsDto;
import org.back.systemklinikimedycznej.patient.exceptions.PatientException;
import org.back.systemklinikimedycznej.patient.repositories.PatientDetailsRepository;
import org.back.systemklinikimedycznej.patient.repositories.entities.PatientDetails;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_card.PatientCard;
import org.back.systemklinikimedycznej.patient.util.PatientDetailsManagerUtil;
import org.back.systemklinikimedycznej.patient.validators.PatientDetailsValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PatientDetailsService {
    private final PatientCardService patientCardService;
    private final PatientDetailsValidator patientDetailsValidator;
    private final PatientDetailsRepository patientDetailsRepository;

    @Transactional
    public PatientDetails create(PatientDetailsDto collectedPatientData, String pesel) {
        PatientCard patientCard = patientCardService.findPatientCardWithPesel(pesel);

        patientDetailsValidator.validatePersonalDetailsNotExistForPatientCard(patientCard);

        PatientDetails patientDetailsToCreate = PatientDetailsManagerUtil.buildPatientDetails(patientCard, collectedPatientData);
        PatientDetails savedPatientDetails = patientDetailsRepository.save(patientDetailsToCreate);

        patientCard.setPatientDetails(patientDetailsToCreate);
        return savedPatientDetails;
    }

    public PatientDetails findPatientDetailsByPesel(String pesel) {
        return patientDetailsRepository.findPatientDetailsByPesel(pesel)
                .orElse(null);
    }

    public PatientDetails update(PatientDetails patientDetailsToUpdate, PatientDetailsDto collectedPatientData) {
        PatientDetailsManagerUtil.updatePatientDetails(patientDetailsToUpdate,collectedPatientData);

        return patientDetailsRepository.save(patientDetailsToUpdate);
    }

    public PatientDetails delete(String pesel) {
        PatientCard patientCard = patientCardService.findPatientCardWithPesel(pesel);
        PatientDetails patientDetailsToRemove = patientCard.getPatientDetails();

        patientCard.removePersonalDetailsId();
        patientDetailsRepository.delete(patientDetailsToRemove);

        return patientDetailsToRemove;
    }

    public PatientDetails findById(Long id) {
        return patientDetailsRepository.findById(id).orElseThrow(
                () -> new PatientException("Nie znaleziono danych pacjenta!", HttpStatus.NOT_FOUND)
        );
    }
}
