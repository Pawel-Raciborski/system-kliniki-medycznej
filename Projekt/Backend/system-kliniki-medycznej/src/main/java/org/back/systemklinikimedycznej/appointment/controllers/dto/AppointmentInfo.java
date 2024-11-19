package org.back.systemklinikimedycznej.appointment.controllers.dto;

import lombok.Builder;
import org.back.systemklinikimedycznej.doctor.controller.dto.DoctorDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Builder
public record AppointmentInfo(
        UUID id,
        LocalDate date,
        LocalTime hour,
        String status

) {
}
