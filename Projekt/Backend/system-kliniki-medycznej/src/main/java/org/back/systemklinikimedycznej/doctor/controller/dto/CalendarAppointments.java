package org.back.systemklinikimedycznej.doctor.controller.dto;

import java.util.List;

public record CalendarAppointments(
        List<TodayCalendarAppointments> calendarAppointments
) {
}
