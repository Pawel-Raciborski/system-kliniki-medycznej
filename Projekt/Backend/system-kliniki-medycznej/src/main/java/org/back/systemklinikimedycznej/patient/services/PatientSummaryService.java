package org.back.systemklinikimedycznej.patient.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.appointment.repositories.entities.Appointment;
import org.back.systemklinikimedycznej.appointment.services.AppointmentService;
import org.back.systemklinikimedycznej.doctor.services.DoctorService;
import org.back.systemklinikimedycznej.patient.controllers.dto.SummaryResponse;
import org.back.systemklinikimedycznej.patient.mapper.PatientDetailsMapper;
import org.back.systemklinikimedycznej.patient.repositories.entities.Patient;
import org.back.systemklinikimedycznej.patient.repositories.entities.PatientDetails;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_card.PatientCard;
import org.back.systemklinikimedycznej.personal_details.repositories.entities.PersonalDetails;
import org.back.systemklinikimedycznej.prescription.services.PrescriptionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientSummaryService {
    private final DoctorService doctorService;
    private final AppointmentService appointmentService;
    private final PrescriptionService prescriptionService;
    private final PatientDiseaseService patientDiseaseService;

    @Transactional
    public SummaryResponse createSummary(PatientCard patientCard) {
        Patient patient = patientCard.getPatient();
        PersonalDetails personalDetails = patient.getPersonalDetails();
        PatientDetails patientDetails = patientCard.getPatientDetails();

        Long numberOfDoctors = doctorService.countAvailableDoctors();
        Long numberOfAppointments = appointmentService.countUpcomingAppointmentsForPatient(patientCard);
        Long numberOfPrescriptions = prescriptionService.countPatientPrescriptions(patient);
        Long numberOfHospitalizations = patientDiseaseService.countPatientDiseases(patientCard);

        String appointmentDate = formatAppointmentDate(appointmentService.findNextPatientAppointment(patientCard));

        return SummaryResponse.builder()
                .fullName("%s %s".formatted(personalDetails.getName(), personalDetails.getSurname()))
                .birthDate(personalDetails.getBirthDate())
                .pesel(personalDetails.getPesel())
                .gender(personalDetails.getGender())
                .patientDetails(PatientDetailsMapper.INSTANCE.mapFromEntity(patientDetails))
                .nextAppointmentDate(appointmentDate)
                .numberOfDoctors(numberOfDoctors)
                .numberOfAppointments(numberOfAppointments)
                .numberOfPrescriptions(numberOfPrescriptions)
                .numberOfHospitalizations(numberOfHospitalizations)
                .build();
    }

    private String formatAppointmentDate(Optional<Appointment> nextPatientAppointment) {
        return nextPatientAppointment.map(appointment -> appointment.getAppointmentDateTime().toString()).orElse("brak");
    }
}
