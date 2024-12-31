package org.back.systemklinikimedycznej.receptionist.controller.dto;

public record ReceptionistInfo(
        Long id,
        String name,
        String surname,
        String email,
        String phoneNumber,
        String dateOfEmployment
) {
}
