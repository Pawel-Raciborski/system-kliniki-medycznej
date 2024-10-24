package org.back.systemklinikimedycznej.appointment.controllers.dto;

import org.back.systemklinikimedycznej.doctor.controller.dto.DoctorDto;

import java.time.LocalDateTime;
import java.util.UUID;

public record AppointmentInfo(
        UUID id,
        LocalDateTime appointmentDateTime,
        String status

) {
}
