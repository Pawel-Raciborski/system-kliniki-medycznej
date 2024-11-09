package org.back.systemklinikimedycznej.patient.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.appointment.controllers.dto.PatientAppointmentInfo;
import org.back.systemklinikimedycznej.appointment.repositories.AppointmentRepository;
import org.back.systemklinikimedycznej.appointment.repositories.entities.Appointment;
import org.back.systemklinikimedycznej.appointment.util.AppointmentManagerUtil;
import org.back.systemklinikimedycznej.patient.controllers.dto.PatientPesel;
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
            return AppointmentManagerUtil.buildUpcomingAppointmentInfo(nextAppointment.get());
        }

        return PatientAppointmentInfo.builder()
                .doctorName("-")
                .doctorSurname("-")
                .appointmentStatus("-")
                .build();
    }

    @Transactional
    public List<PatientAppointmentInfo> findUpcomingAppointments(Integer page, Integer pageSize, String pesel) {
        Pageable pageable = PageRequest.of(page,pageSize).withSort(Sort.by("appointmentDateTime").descending());

        return appointmentRepository.findUpcomingAppointments(pageable,pesel).stream()
                .map(AppointmentManagerUtil::buildUpcomingAppointmentInfo)
                .toList();
    }
}
