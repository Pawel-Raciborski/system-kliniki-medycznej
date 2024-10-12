package org.back.systemklinikimedycznej.appointment.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.appointment.controllers.dto.AppointmentDto;
import org.back.systemklinikimedycznej.appointment.repositories.AppointmentRepository;
import org.back.systemklinikimedycznej.appointment.repositories.entities.Appointment;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_card.PatientCard;
import org.back.systemklinikimedycznej.patient.services.PatientCardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final PatientCardService patientCardService;

    @Transactional
    public Appointment createForRegisteredUser(AppointmentDto appointmentDto) {
        PatientCard patientCard = patientCardService.findCardForPatientWithEmail(appointmentDto.patientEmail());

        return null;
    }
}
