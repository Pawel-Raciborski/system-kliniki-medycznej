package org.back.systemklinikimedycznej.patient.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.appointment.controllers.dto.PatientAppointmentInfo;
import org.back.systemklinikimedycznej.appointment.repositories.AppointmentRepository;
import org.back.systemklinikimedycznej.appointment.repositories.entities.Appointment;
import org.back.systemklinikimedycznej.appointment.util.AppointmentManagerUtil;
import org.back.systemklinikimedycznej.patient.controllers.dto.PatientPesel;
import org.back.systemklinikimedycznej.patient.repositories.entities.Patient;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientAppointmentService {
    private final AppointmentRepository appointmentRepository;
    @Transactional
    public PatientAppointmentInfo findNextAppointmentForPatientWithPesel(PatientPesel patientPesel, LocalDate currentDate) {
        Optional<Appointment> nextAppointment = appointmentRepository.findNextAppointmentForPatient(patientPesel.pesel(),currentDate);

        if(nextAppointment.isPresent()){
            return AppointmentManagerUtil.buildPatientAppointmentInfo(nextAppointment.get());
        }

        return PatientAppointmentInfo.builder()
                .doctorName("-")
                .doctorSurname("-")
                .appointmentStatus("-")
                .build();
    }

    @Transactional
    public List<PatientAppointmentInfo> findUpcomingAppointments(Integer page, Integer pageSize, Patient patient) {
        Pageable pageable = buildPageRequest(page, pageSize).withSort(Sort.by("appointmentDateTime").ascending());
        String pesel = patient.getPersonalDetails().getPesel();

        return appointmentRepository.findUpcomingAppointments(pageable,pesel).stream()
                .map(AppointmentManagerUtil::buildPatientAppointmentInfo)
                .toList();
    }

    public List<PatientAppointmentInfo> findFinishedAppointments(Patient patient, Integer page, Integer pageSize) {
        Pageable pageable = buildPageRequest(page,pageSize).withSort(Sort.by("appointmentDateTime").descending());

        return appointmentRepository.findPatientFinishedAppointments(patient.getPersonalDetails().getPesel(),pageable)
                .getContent().stream()
                .map(AppointmentManagerUtil::buildPatientAppointmentInfo)
                .toList();
    }

    private static PageRequest buildPageRequest(Integer page, Integer pageSize) {
        return PageRequest.of(page, pageSize);
    }
}
