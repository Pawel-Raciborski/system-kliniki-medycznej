package org.back.systemklinikimedycznej.patient.util;

import org.back.systemklinikimedycznej.patient.repositories.entities.Patient;
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
}
