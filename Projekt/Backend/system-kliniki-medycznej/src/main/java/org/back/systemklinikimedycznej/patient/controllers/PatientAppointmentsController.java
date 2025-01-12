package org.back.systemklinikimedycznej.patient.controllers;


import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.appointment.controllers.dto.PatientAppointmentInfo;
import org.back.systemklinikimedycznej.config.util.DateFormatter;
import org.back.systemklinikimedycznej.patient.controllers.dto.PatientPesel;
import org.back.systemklinikimedycznej.patient.repositories.entities.Patient;
import org.back.systemklinikimedycznej.patient.services.PatientAppointmentService;
import org.back.systemklinikimedycznej.patient.services.PatientService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/patient-appointments")
@RequiredArgsConstructor
public class PatientAppointmentsController {
    private final PatientAppointmentService patientAppointmentService;
    private final PatientService patientService;
    @GetMapping("/next-appointment")
    public ResponseEntity<PatientAppointmentInfo> getNextAppointmentInfoForPatient(
            @RequestParam(name="currentDate") @DateTimeFormat(pattern = DateFormatter.DATE_FORMAT) LocalDate currentDate,
            @RequestBody PatientPesel patientPesel
    ) {
        PatientAppointmentInfo nextAppointment = patientAppointmentService.findNextAppointmentForPatientWithPesel(patientPesel, currentDate);

        return ResponseEntity.ok(nextAppointment);
    }

    @GetMapping
    public ResponseEntity<List<PatientAppointmentInfo>> findPatientUpcomingAppointments(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(name = "patientId") Long patientId
    ){
        Patient patient = patientService.findById(patientId);
        var upcomingPatientAppointments = patientAppointmentService.findUpcomingAppointments(page,pageSize,patient);

        return ResponseEntity.ok(upcomingPatientAppointments);
    }

    @GetMapping("/finished")
    public ResponseEntity<List<PatientAppointmentInfo>> findPatientFinishedAppointments(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(name = "patientId") Long patientId
    ){
        Patient patient = patientService.findById(patientId);
        List<PatientAppointmentInfo> finishedAppointments = patientAppointmentService.findFinishedAppointments(patient, page, pageSize);

        return ResponseEntity.ok(finishedAppointments);
    }
}
