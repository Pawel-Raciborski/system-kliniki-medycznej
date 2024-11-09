package org.back.systemklinikimedycznej.doctor.controller.dto;

import lombok.With;
import org.back.systemklinikimedycznej.personal_details.dto.PersonalDetailsDto;
import org.back.systemklinikimedycznej.account.dto.AccountDto;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@With
public record RegisterDoctorDetails(
        AccountDto registerAccountData,
        PersonalDetailsDto personalDetails,
        String pwzNumber,
        String description,
        LocalDate dateOfEmployment,
        List<DoctorSpecializationDto> doctorSpecializations
){
}
