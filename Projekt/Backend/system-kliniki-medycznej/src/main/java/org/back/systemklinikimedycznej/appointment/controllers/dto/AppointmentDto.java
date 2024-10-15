package org.back.systemklinikimedycznej.appointment.controllers.dto;

import org.back.systemklinikimedycznej.doctor.controller.dto.DoctorInfo;

import java.time.LocalDateTime;

public record AppointmentDto(
        String patientPesel,
        DoctorInfo selectedDoctor,
        LocalDateTime appointmentDateTime

) {
}
