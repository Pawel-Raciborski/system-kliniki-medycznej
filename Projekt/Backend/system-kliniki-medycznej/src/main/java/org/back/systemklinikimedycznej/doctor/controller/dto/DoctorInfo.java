package org.back.systemklinikimedycznej.doctor.controller.dto;

public record DoctorInfo(
        Long id,
        String name,
        String surname,
        String email,
        String pwzNumber,
        String phoneNumber,
        String description
) {
}
