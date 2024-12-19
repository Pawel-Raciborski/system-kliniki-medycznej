package org.back.systemklinikimedycznej.appointment.controllers.dto;

import org.back.systemklinikimedycznej.patient.controllers.dto.CreatePatientDiseaseRequest;
import org.back.systemklinikimedycznej.prescription.dto.CreatePrescriptionRequest;

import java.util.List;
import java.util.UUID;

public record FinishAppointmentRequest(
        UUID appointmentId,
        String diagnosis,
        List<CreatePrescriptionRequest> appointmentPrescriptions,
        List<CreatePatientDiseaseRequest> patientDiseases
) {

}
