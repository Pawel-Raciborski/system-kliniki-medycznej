package org.back.systemklinikimedycznej.patient.controllers;


import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.appointment.controllers.dto.PatientAppointmentInfo;
import org.back.systemklinikimedycznej.config.util.DateFormatter;
import org.back.systemklinikimedycznej.patient.controllers.dto.PatientPesel;
import org.back.systemklinikimedycznej.patient.services.PatientAppointmentService;
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
    @GetMapping("/next-appointment")
    public ResponseEntity<PatientAppointmentInfo> getNextAppointmentInfoForPatient(
            @RequestParam(name="currentDate") @DateTimeFormat(pattern = DateFormatter.DATE_FORMAT) LocalDate currentDate,
            @RequestBody PatientPesel patientPesel
    ) {
        PatientAppointmentInfo nextAppointment = patientAppointmentService.findNextAppointmentForPatientWithPesel(patientPesel, currentDate);

        return ResponseEntity.ok(nextAppointment);
    }

    @GetMapping
    public ResponseEntity<List<PatientAppointmentInfo>> getUpcomingAppointments(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestBody PatientPesel patientPesel
    ){
        var upcomingPatientAppointments = patientAppointmentService.findUpcomingAppointments(page,pageSize,patientPesel.pesel());

        return ResponseEntity.ok(upcomingPatientAppointments);
    }
}
