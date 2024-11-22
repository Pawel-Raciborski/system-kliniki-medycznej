package org.back.systemklinikimedycznej.appointment.util;

import org.back.systemklinikimedycznej.appointment.controllers.dto.AppointmentDetails;
import org.back.systemklinikimedycznej.appointment.controllers.dto.AppointmentInfo;
import org.back.systemklinikimedycznej.appointment.controllers.dto.PatientAppointmentInfo;
import org.back.systemklinikimedycznej.appointment.domain.AppointmentStatus;
import org.back.systemklinikimedycznej.appointment.mappers.AppointmentMapper;
import org.back.systemklinikimedycznej.appointment.repositories.entities.Appointment;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.back.systemklinikimedycznej.patient.mapper.PatientMapper;
import org.back.systemklinikimedycznej.patient.repositories.entities.Patient;
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

    public static PatientAppointmentInfo buildUpcomingAppointmentInfo(Appointment appointment) {
        return PatientAppointmentInfo.builder()
                .id(appointment.getId())
                .doctorName(appointment.getDoctor().getPersonalDetails().getName())
                .doctorSurname(appointment.getDoctor().getPersonalDetails().getSurname())
                .appointmentDate(appointment.getAppointmentDateTime())
                .appointmentStatus(appointment.getStatus().getAppointmentStatusName())
                .build();
    }

    public static void updateStatus(Appointment appointmentToUpdate, AppointmentStatus newStatus) {
        appointmentToUpdate.setStatus(newStatus);
    }

    public static AppointmentInfo buildAppointmentInfo(Appointment appointment) {
        LocalDateTime date = appointment.getAppointmentDateTime();
        return AppointmentInfo.builder()
                .id(appointment.getId())
                .status(appointment.getStatus().getAppointmentStatusName())
                .date(date.toLocalDate())
                .hour(date.toLocalTime())
                .build();
    }

    public static AppointmentDetails buildAppointmentDetails(Appointment appointment, PatientCard patientCard, Patient patient) {
        return AppointmentDetails.builder()
                .patientCardId(patientCard.getId())
                .appointment(buildAppointmentInfo(appointment))
                .patientData(PatientMapper.INSTANCE.mapFromEntity(patient))
                .build();
    }
}
