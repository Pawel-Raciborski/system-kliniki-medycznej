package org.back.systemklinikimedycznej.patient.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.back.systemklinikimedycznej.account.repositories.entities.Account;
import org.back.systemklinikimedycznej.account.services.AccountService;
import org.back.systemklinikimedycznej.patient.controllers.dto.PatientPesel;
import org.back.systemklinikimedycznej.patient.controllers.dto.PatientToRegisterCredentials;
import org.back.systemklinikimedycznej.patient.controllers.dto.PatientToRegisterData;
import org.back.systemklinikimedycznej.patient.controllers.dto.RegisteredPatientAccount;
import org.back.systemklinikimedycznej.patient.exceptions.PatientException;
import org.back.systemklinikimedycznej.patient.repositories.PatientRepository;
import org.back.systemklinikimedycznej.patient.repositories.entities.Patient;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_card.PatientCard;
import org.back.systemklinikimedycznej.patient.util.PatientManagerUtil;
import org.back.systemklinikimedycznej.patient.validators.PatientValidator;
import org.back.systemklinikimedycznej.personal_details.repositories.entities.PersonalDetails;
import org.back.systemklinikimedycznej.personal_details.services.PersonalDetailsService;
import org.back.systemklinikimedycznej.role.enums.BasicAppRoles;
import org.back.systemklinikimedycznej.role.services.AccountRoleService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final PersonalDetailsService personalDetailsService;
    private final PatientValidator patientValidator;
    private final PatientCardService patientCardService;
    private final AccountService accountService;
    private final AccountRoleService accountRoleService;

    /**
     * Registers patient which want to be permanently clinic patient
     *
     * @param patientToRegister registered patient data
     * @return Returns registered patient
     */
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

    @Transactional
    public void delete(PatientPesel patientPesel) {
        Patient patientToRemove = findByPesel(patientPesel.pesel());
        PersonalDetails personalDetailsToRemove = patientToRemove.getPersonalDetails();

        patientCardService.deleteCardForPatient(patientToRemove);
        patientRepository.delete(patientToRemove);
        personalDetailsService.deletePersonalDetails(personalDetailsToRemove);
    }

    public Patient findByPesel(String pesel) {
        return patientRepository.findByPersonalDetailsPesel(pesel)
                .orElseThrow(() -> new PatientException("Nie znaleziono pacjenta z podanym peselem!", HttpStatus.NOT_FOUND));
    }

    public Patient findById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new PatientException("Nie znaleziono podanego pacjenta!", HttpStatus.NOT_FOUND));
    }

    public List<Patient> findAllPaged(Integer page, Integer pageSize) {
        Sort sort = Sort.by(Sort.Direction.ASC, "personalDetails.pesel");
        Pageable pageable = PageRequest.of(page, pageSize, sort);

        return patientRepository.findAll(pageable).getContent();
    }

    @Transactional
    public RegisteredPatientAccount createAccountForPatient(PatientToRegisterCredentials patientToRegisterCredentials, Patient patient) {
        if(Objects.nonNull(patient.getAccount())){
            throw new PatientException("Podany pacjent posiada już konto!",HttpStatus.CONFLICT);
        }

        Account createdPatientAccount = accountService.create(patientToRegisterCredentials.accountCredentials());

        PatientManagerUtil.setPatientAccount(patient, createdPatientAccount);

        patientRepository.save(patient);
        accountRoleService.processAccountRoleCreation(createdPatientAccount, BasicAppRoles.PATIENT.name());
        return PatientManagerUtil.buildRegisteredPatientAccountData(patient);
    }

    public Patient findByAccount(Account account) {
        return patientRepository.findByAccount(account)
                .orElseThrow(
                        () -> new PatientException("Nie znaleziono konta pacjenta!",HttpStatus.NOT_FOUND)
                );
    }

    public List<Patient> searchPatientsWithPesel(String pesel) {
        if(pesel.isBlank() || pesel.isEmpty()){
           return patientRepository.findAll();
        }
        return patientRepository.findPatientsStartsWithPesel(pesel);
    }
}
