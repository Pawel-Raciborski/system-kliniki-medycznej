package org.back.systemklinikimedycznej.doctor.controller.dto;

import java.time.LocalDate;
import java.util.List;

public record CalendarAppointment(
        LocalDate date,
        List<CalendarAppointmentInfo> calendarAppointments,
        OfficeHoursDto officeHoursDto
) {
}
