package org.back.systemklinikimedycznej.doctor.controller.dto;

import org.back.systemklinikimedycznej.personal_details.dto.PersonalDetailsDto;

import java.util.List;

public record DoctorDto(
        String pesel,
        String name,
        String surname,
        String email,
        String pwzNumber,
        String description,
        String phoneNumber,
        String street,
        String postalCode,
        String apartmentNumber,
        String city,
        List<DoctorSpecializationDto> doctorSpecializations

) {
}
