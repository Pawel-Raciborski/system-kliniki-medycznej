package org.back.systemklinikimedycznej.appointment.controllers.dto;

import lombok.Builder;
import org.back.systemklinikimedycznej.doctor.controller.dto.DoctorInfo;
import org.back.systemklinikimedycznej.patient.controllers.dto.PatientData;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_card.PatientCard;

import java.util.UUID;

@Builder
public record AppointmentDetails(
        AppointmentInfo appointment,
        PatientData patientData,
        DoctorInfo doctor,
        UUID patientCardId
) {
}
