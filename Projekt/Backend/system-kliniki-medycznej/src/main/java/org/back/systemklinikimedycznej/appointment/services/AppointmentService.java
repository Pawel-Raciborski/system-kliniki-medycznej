package org.back.systemklinikimedycznej.appointment.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.appointment.controllers.dto.AppointmentDto;
import org.back.systemklinikimedycznej.appointment.domain.AppointmentStatus;
import org.back.systemklinikimedycznej.appointment.exceptions.AppointmentException;
import org.back.systemklinikimedycznej.appointment.repositories.AppointmentRepository;
import org.back.systemklinikimedycznej.appointment.repositories.entities.Appointment;
import org.back.systemklinikimedycznej.appointment.util.AppointmentManagerUtil;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.back.systemklinikimedycznej.doctor.services.DoctorService;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_card.PatientCard;
import org.back.systemklinikimedycznej.patient.services.PatientCardService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

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
        return appointmentRepository.findAllBetweenDatesForADoctor(startDate,endDate,doctor.getPwzNumber());
    }

    public Appointment findById(UUID id) {
        return appointmentRepository.findById(id).orElseThrow(
                () -> new AppointmentException("Nie znaleziono wizyty o id: %s".formatted(id), HttpStatus.NOT_FOUND));
    }

    public Appointment updateStatus(Appointment appointmentToUpdate, AppointmentStatus newStatus) {
        AppointmentManagerUtil.updateStatus(appointmentToUpdate,newStatus);

        return appointmentRepository.save(appointmentToUpdate);
    }

    public List<LocalTime> findBookedAppointmentHoursForADoctorInGivenDay(Doctor doctor, LocalDate date) {
        return appointmentRepository.findBookedAppointmentHoursForADoctorInGivenDay(doctor.getPwzNumber(),date).stream()
                .map(LocalDateTime::toLocalTime).toList();
    }
}
