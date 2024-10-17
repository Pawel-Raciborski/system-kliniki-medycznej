package org.back.systemklinikimedycznej.doctor.controller.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record DoctorOfficeHoursDto(
        DayOfWeek day,
        LocalTime startHour,
        LocalTime endHour
) {
}
