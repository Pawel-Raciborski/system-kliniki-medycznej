package org.back.systemklinikimedycznej.doctor.controller.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record OfficeHoursDto(
        Long id,
        LocalTime startHour,
        LocalTime endHour,
        DayOfWeek day,
        Integer durationInMinutes
) {
}
