package org.back.systemklinikimedycznej.appointment.controllers.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record UpcomingAppointmentInfo(
        UUID id,
        String doctorName,
        String doctorSurname,
        LocalDateTime appointmentDate,
        String appointmentStatus
) {
}
