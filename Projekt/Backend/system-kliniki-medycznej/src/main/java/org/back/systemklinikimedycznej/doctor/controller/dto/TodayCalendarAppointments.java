package org.back.systemklinikimedycznej.doctor.controller.dto;

import lombok.Builder;
import org.back.systemklinikimedycznej.appointment.controllers.dto.AppointmentDto;
import org.back.systemklinikimedycznej.appointment.controllers.dto.AppointmentInfo;
import org.back.systemklinikimedycznej.appointment.repositories.entities.Appointment;

import java.util.List;

@Builder
public record TodayCalendarAppointments(
        String day,
        List<AppointmentInfo> appointments
) {
}
