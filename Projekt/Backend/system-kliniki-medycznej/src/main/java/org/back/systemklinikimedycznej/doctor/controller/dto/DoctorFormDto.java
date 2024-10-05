package org.back.systemklinikimedycznej.doctor.controller.dto;

import org.back.systemklinikimedycznej.personal_details.dto.PersonalDetailsDto;
import org.back.systemklinikimedycznej.account.dto.AccountDto;

import java.time.LocalDate;
import java.util.List;

public record DoctorFormDto(
        AccountDto registerAccountData,
        PersonalDetailsDto personalDetails,
        String pwzNumber,
        String description,
        LocalDate dateOfEmployment,
        List<DoctorSpecializationDto> doctorSpecializations
){
}
