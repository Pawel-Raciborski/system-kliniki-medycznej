package org.back.systemklinikimedycznej.doctor.util;

import lombok.experimental.UtilityClass;
import org.back.systemklinikimedycznej.account.repositories.entities.Account;
import org.back.systemklinikimedycznej.doctor.controller.dto.DoctorFormDto;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.back.systemklinikimedycznej.personal_details.repositories.entities.PersonalDetails;

@UtilityClass
public class DoctorManagerUtil {

    public static Doctor buildDoctor(DoctorFormDto doctorFormDto, Account createdDoctorAccount, PersonalDetails doctorPersonalDetails) {
        return Doctor.builder()
                .account(createdDoctorAccount)
                .personalDetails(doctorPersonalDetails)
                .description(doctorFormDto.description())
                .pwzNumber(doctorFormDto.pwzNumber())
                .dateOfEmployment(doctorFormDto.dateOfEmployment())
                .build();
    }
}
