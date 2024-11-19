package org.back.systemklinikimedycznej.doctor.controller.dto;

import java.util.List;

public record CalendarAppointmentsResponse(
        List<CalendarAppointment> calendarAppointments
) {
}
