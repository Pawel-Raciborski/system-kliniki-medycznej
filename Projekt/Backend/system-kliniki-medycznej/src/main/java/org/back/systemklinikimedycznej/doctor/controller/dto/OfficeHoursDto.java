package org.back.systemklinikimedycznej.doctor.controller.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record OfficeHoursDto(
        LocalTime startTime,
        LocalTime endTime,
        DayOfWeek day
) {
}
