package org.back.systemklinikimedycznej.doctor.controller.dto;

import org.back.systemklinikimedycznej.personal_details.dto.PersonalDetailsForm;
import org.back.systemklinikimedycznej.user.dto.RegisterAccountForm;

import java.time.LocalDate;
import java.util.List;

public record RegisterDoctorForm(
        RegisterAccountForm registerAccountData,
        PersonalDetailsForm personalDetails,
        String pwzNumber,
        LocalDate dateOfEmployment,
        List<DoctorSpecializationDto> doctorSpecializations
){
}
