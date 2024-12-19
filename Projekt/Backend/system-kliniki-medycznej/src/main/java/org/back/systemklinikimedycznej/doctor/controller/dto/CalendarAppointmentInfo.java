package org.back.systemklinikimedycznej.doctor.controller.dto;

import lombok.Builder;

import java.time.LocalTime;
import java.util.UUID;

@Builder
public record CalendarAppointmentInfo(
        UUID id,
        LocalTime hour,
        String status,
        String patientFullName
) {

}
