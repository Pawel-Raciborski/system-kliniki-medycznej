package org.back.systemklinikimedycznej.appointment.controllers.dto;

import org.back.systemklinikimedycznej.doctor.controller.dto.DoctorDto;

import java.time.LocalDateTime;

public record AppointmentInfo(
        LocalDateTime appointmentDateTime,
        String status

) {
}
