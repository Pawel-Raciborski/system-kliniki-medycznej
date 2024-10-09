package org.back.systemklinikimedycznej.receptionist.controller.dto;

public record ReceptionistDto(
        String name,
        String surname,
        String email,
        String phoneNumber,
        String street,
        String postalCode,
        String apartmentNumber,
        String city
) {
}
