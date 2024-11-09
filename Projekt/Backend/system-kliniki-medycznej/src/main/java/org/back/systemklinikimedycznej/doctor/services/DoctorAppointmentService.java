package org.back.systemklinikimedycznej.doctor.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.appointment.repositories.entities.Appointment;
import org.back.systemklinikimedycznej.appointment.services.AppointmentService;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DoctorAppointmentService {
    private final AppointmentService appointmentService;

    @Transactional
    public Doctor getDoctorAppointmentInfo(String appointmentId) {
        Appointment appointment = appointmentService.findById(UUID.fromString(appointmentId));
        return appointment.getDoctor();
    }
}
