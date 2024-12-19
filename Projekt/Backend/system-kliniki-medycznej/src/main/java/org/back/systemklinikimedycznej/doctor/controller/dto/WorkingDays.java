package org.back.systemklinikimedycznej.doctor.controller.dto;

import java.util.List;

public record WorkingDays(
        Integer day,
        List<String> hours
) {
}
