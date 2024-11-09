package org.back.systemklinikimedycznej.appointment.controllers.dto;

import org.back.systemklinikimedycznej.doctor.controller.dto.DoctorInfo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record AppointmentDto(
        String patientPesel,
        String doctorPwzNumber,
        LocalDate date,
        LocalTime hour

) {
}
