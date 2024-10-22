package org.back.systemklinikimedycznej.appointment.util;

import org.back.systemklinikimedycznej.appointment.repositories.entities.Appointment;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_card.PatientCard;

import java.time.LocalDateTime;

public class AppointmentManagerUtil {
    public static Appointment buildAppointment(PatientCard patientCard, Doctor doctor, LocalDateTime appointmentDate) {
        return Appointment.builder()
                .doctor(doctor)
                .doctorCalendar(doctor.getCalendar())
                .patientCard(patientCard)
                .appointmentDateTime(appointmentDate)
                .build();
    }
}
