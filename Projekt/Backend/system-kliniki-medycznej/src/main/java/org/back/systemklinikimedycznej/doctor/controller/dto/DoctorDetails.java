package org.back.systemklinikimedycznej.doctor.controller.dto;

import org.back.systemklinikimedycznej.doctor.repositories.entities.DoctorOfficeHours;
import org.back.systemklinikimedycznej.personal_details.dto.PersonalDetailsDto;

import java.time.LocalDate;
import java.util.List;

public record DoctorDetails(
        String username,
        String email,
        PersonalDetailsDto personalDetails,
        String description,
        List<DoctorSpecializationDto> doctorSpecializations,
        List<DoctorOfficeHoursDto> doctorOfficeHours,
        String pwzNumber,
        LocalDate dateOfEmployment

) {
}
