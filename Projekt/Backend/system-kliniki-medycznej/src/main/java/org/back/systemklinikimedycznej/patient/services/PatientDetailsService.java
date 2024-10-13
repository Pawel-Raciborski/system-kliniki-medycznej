package org.back.systemklinikimedycznej.patient.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.patient.controllers.dto.CollectedPatientData;
import org.back.systemklinikimedycznej.patient.controllers.dto.PatientDetailsDto;
import org.back.systemklinikimedycznej.patient.controllers.dto.PatientPesel;
import org.back.systemklinikimedycznej.patient.exceptions.PersonalDetailsException;
import org.back.systemklinikimedycznej.patient.repositories.PatientDetailsRepository;
import org.back.systemklinikimedycznej.patient.repositories.entities.PatientDetails;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_card.PatientCard;
import org.back.systemklinikimedycznej.patient.util.PatientCardManagerUtil;
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
    public PatientDetails create(CollectedPatientData collectedPatientData) {
        PatientCard patientCard = patientCardService.findPatientCardWithPesel(collectedPatientData.patientPesel());

        patientDetailsValidator.validatePersonalDetailsNotExistForPatientCard(patientCard);

        PatientDetails patientDetailsToCreate = PatientDetailsManagerUtil.buildPatientDetails(patientCard, collectedPatientData);
        PatientDetails savedPatientDetails = patientDetailsRepository.save(patientDetailsToCreate);

        patientCard.setPatientDetails(patientDetailsToCreate);
        return savedPatientDetails;
    }

    public PatientDetails findPatientDetailsByPesel(String pesel) {
        return patientDetailsRepository.findPatientDetailsByPesel(pesel)
                .orElseThrow(() -> new PersonalDetailsException("Nie znaleziono danych dla pacjenta o peselu %s".formatted(pesel), HttpStatus.NOT_FOUND));
    }

    public PatientDetails update(CollectedPatientData collectedPatientData) {
        PatientDetails patientDetailsToUpdate = findPatientDetailsByPesel(collectedPatientData.patientPesel());

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
}
