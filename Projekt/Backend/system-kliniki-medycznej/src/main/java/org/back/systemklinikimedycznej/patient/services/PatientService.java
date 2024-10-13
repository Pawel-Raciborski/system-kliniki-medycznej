package org.back.systemklinikimedycznej.patient.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.back.systemklinikimedycznej.patient.controllers.dto.PatientToRegisterData;
import org.back.systemklinikimedycznej.patient.repositories.PatientRepository;
import org.back.systemklinikimedycznej.patient.repositories.entities.Patient;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_card.PatientCard;
import org.back.systemklinikimedycznej.patient.util.PatientManagerUtil;
import org.back.systemklinikimedycznej.patient.validators.PatientValidator;
import org.back.systemklinikimedycznej.personal_details.repositories.entities.PersonalDetails;
import org.back.systemklinikimedycznej.personal_details.services.PersonalDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final PersonalDetailsService personalDetailsService;
    private final PatientValidator patientValidator;
    private final PatientCardService patientCardService;

    @Transactional
    public Patient registerPatient(PatientToRegisterData patientToRegister) {
        PersonalDetails createdPatientPersonalDetails = personalDetailsService.create(patientToRegister.personalDetails());

        Patient patientToCreate = PatientManagerUtil.buildPatient(createdPatientPersonalDetails, patientToRegister.parentPesel());
        patientValidator.validateHasParentPeselIfPatientIsNotAnAdult(patientToCreate);

        Patient createdPatient = patientRepository.save(patientToCreate);
        log.info("Patient created with id: {}", createdPatient.getId());

        PatientCard cardForPatient = patientCardService.createCardForPatient(createdPatient);
        log.info("Patient card created with id {}", cardForPatient.getId());
        return createdPatient;
    }
}
