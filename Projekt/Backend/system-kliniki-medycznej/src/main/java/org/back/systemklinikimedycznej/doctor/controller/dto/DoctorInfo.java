package org.back.systemklinikimedycznej.doctor.controller.dto;

public record DoctorInfo(
        String name,
        String surname,
        String email,
        String phoneNumber,
        String description
) {
}
