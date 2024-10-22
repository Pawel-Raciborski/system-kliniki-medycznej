package org.back.systemklinikimedycznej.appointment.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AppointmentStatus {
    SCHEDULED("Umówiono"),
    CONFIRMED("Potwierdzono"),
    CREATED("Utworzono"),
    IN_PROGRESS("W trakcie"),
    CHECK_OUT("Zakończona"),
    CANCELLED("Anulowana");

    private final String appointmentStatusName;
}
