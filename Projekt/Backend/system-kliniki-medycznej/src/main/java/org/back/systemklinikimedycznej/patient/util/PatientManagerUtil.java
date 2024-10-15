package org.back.systemklinikimedycznej.patient.util;

import org.back.systemklinikimedycznej.account.mapper.AccountMapper;
import org.back.systemklinikimedycznej.account.repositories.entities.Account;
import org.back.systemklinikimedycznej.patient.controllers.dto.RegisteredPatientAccount;
import org.back.systemklinikimedycznej.patient.repositories.entities.Patient;
import org.back.systemklinikimedycznej.personal_details.mapper.PersonalDetailsMapper;
import org.back.systemklinikimedycznej.personal_details.repositories.entities.PersonalDetails;

import java.time.LocalDateTime;

public class PatientManagerUtil {
    public static Patient buildPatient(PersonalDetails personalDetails, String parentPesel) {
        return Patient.builder()
                .dateOfRegistration(LocalDateTime.now())
                .parentPesel(parentPesel)
                .personalDetails(personalDetails)
                .build();
    }

    public static void setPatientAccount(Patient patient, Account createdPatientAccount) {
        patient.setAccount(createdPatientAccount);
    }

    public static RegisteredPatientAccount buildRegisteredPatientAccountData(Account account, PersonalDetails personalDetails) {
        return RegisteredPatientAccount.builder()
                .accountDto(AccountMapper.INSTANCE.mapFromEntity(account))
                .personalDetailsDto(PersonalDetailsMapper.INSTANCE.mapFromEntity(personalDetails))
                .build();

    }
}
