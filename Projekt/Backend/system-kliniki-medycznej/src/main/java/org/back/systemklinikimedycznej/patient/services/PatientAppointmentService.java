package org.back.systemklinikimedycznej.patient.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.appointment.controllers.dto.UpcomingAppointmentInfo;
import org.back.systemklinikimedycznej.appointment.repositories.AppointmentRepository;
import org.back.systemklinikimedycznej.appointment.repositories.entities.Appointment;
import org.back.systemklinikimedycznej.appointment.services.AppointmentService;
import org.back.systemklinikimedycznej.appointment.util.AppointmentManagerUtil;
import org.back.systemklinikimedycznej.patient.controllers.dto.PatientPesel;
import org.back.systemklinikimedycznej.patient.repositories.entities.Patient;
import org.back.systemklinikimedycznej.patient.util.PatientManagerUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientAppointmentService {
    private final AppointmentRepository appointmentRepository;
    @Transactional
    public UpcomingAppointmentInfo findNextAppointmentForPatientWithPesel(PatientPesel patientPesel, LocalDate currentDate) {
        Optional<Appointment> nextAppointment = appointmentRepository.findNextAppointmentForPatient(patientPesel.pesel(),currentDate);

        if(nextAppointment.isPresent()){
            return AppointmentManagerUtil.buildUpcomingAppointmentInfo(nextAppointment.get());
        }

        return UpcomingAppointmentInfo.builder()
                .doctorName("-")
                .doctorSurname("-")
                .appointmentStatus("-")
                .build();
    }
}
