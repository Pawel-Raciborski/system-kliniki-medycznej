package org.back.systemklinikimedycznej.receptionist.util;

import org.back.systemklinikimedycznej.account.repositories.entities.Account;
import org.back.systemklinikimedycznej.personal_details.repositories.entities.PersonalDetails;
import org.back.systemklinikimedycznej.receptionist.repositories.entities.Receptionist;

import java.time.LocalDate;

public class ReceptionistManagementUtil {
    public static Receptionist buildReceptionist(Account createdReceptionistAccount, PersonalDetails personalDetails, LocalDate dateOfEmployment){
        return Receptionist.builder()
                .account(createdReceptionistAccount)
                .personalDetails(personalDetails)
                .dateOfEmployment(dateOfEmployment)
                .build();
    }
}
