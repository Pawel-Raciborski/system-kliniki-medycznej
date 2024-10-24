package org.back.systemklinikimedycznej.appointment.util;

import org.back.systemklinikimedycznej.appointment.controllers.dto.UpcomingAppointmentInfo;
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

    public static UpcomingAppointmentInfo buildUpcomingAppointmentInfo(Appointment appointment) {
        return UpcomingAppointmentInfo.builder()
                .id(appointment.getId())
                .doctorName(appointment.getDoctor().getPersonalDetails().getName())
                .doctorSurname(appointment.getDoctor().getPersonalDetails().getSurname())
                .appointmentDate(appointment.getAppointmentDateTime())
                .appointmentStatus(appointment.getStatus().getAppointmentStatusName())
                .build();
    }
}
