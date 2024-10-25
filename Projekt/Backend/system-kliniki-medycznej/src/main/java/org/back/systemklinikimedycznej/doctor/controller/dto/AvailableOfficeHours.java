package org.back.systemklinikimedycznej.doctor.controller.dto;

import lombok.Builder;

import java.time.LocalTime;
import java.util.List;

@Builder
public record AvailableOfficeHours(
        List<LocalTime> officeHours
) {
}
