package org.back.systemklinikimedycznej.appointment.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.appointment.controllers.dto.AppointmentDto;
import org.back.systemklinikimedycznej.appointment.domain.AppointmentStatus;
import org.back.systemklinikimedycznej.appointment.repositories.AppointmentRepository;
import org.back.systemklinikimedycznej.appointment.repositories.entities.Appointment;
import org.back.systemklinikimedycznej.appointment.util.AppointmentManagerUtil;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.back.systemklinikimedycznej.doctor.services.DoctorService;
import org.back.systemklinikimedycznej.patient.repositories.entities.Patient;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_card.PatientCard;
import org.back.systemklinikimedycznej.patient.services.PatientCardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final PatientCardService patientCardService;
    private final DoctorService doctorService;

    @Transactional
    public Appointment createScheduledAppointment(AppointmentDto appointmentDto) {
        PatientCard patientCard = patientCardService.findPatientCardWithPesel(appointmentDto.patientPesel());
        Doctor doctor= doctorService.findByPwzNumber(appointmentDto.selectedDoctorPwzNumber());

        Appointment scheduledAppointment = AppointmentManagerUtil.buildAppointment(patientCard,doctor,appointmentDto.appointmentDateTime())
                .withStatus(AppointmentStatus.SCHEDULED);

        return appointmentRepository.save(scheduledAppointment);
    }

    public List<Appointment> findAllAppointmentsBetweenDatesForADoctor(LocalDate startDate, LocalDate endDate, Doctor doctor) {
        return appointmentRepository.findAllBetweenDates(startDate,endDate,doctor.getPwzNumber());
    }
}
