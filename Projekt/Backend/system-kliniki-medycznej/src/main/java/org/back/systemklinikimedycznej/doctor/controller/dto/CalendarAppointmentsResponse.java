package org.back.systemklinikimedycznej.doctor.controller.dto;

import java.time.LocalDate;
import java.util.List;

public record CalendarAppointmentsResponse(
        List<CalendarAppointment> calendarAppointments,
        LocalDate start,
        LocalDate end
) {
}
