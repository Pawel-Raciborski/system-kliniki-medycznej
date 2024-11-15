package org.back.systemklinikimedycznej.patient.controllers.dto;

import lombok.Builder;
import org.back.systemklinikimedycznej.patient.repositories.entities.PatientDetails;
import org.back.systemklinikimedycznej.personal_details.dto.PersonalDetailsDto;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record SummaryResponse(
        String fullName,
        String pesel,
        LocalDate birthDate,
        String gender,
        PatientDetailsDto patientDetails,
        Long numberOfDoctors,
        Long numberOfAppointments,
        Long numberOfPrescriptions,
        Long numberOfHospitalizations,
        String nextAppointmentDate
) {
}
