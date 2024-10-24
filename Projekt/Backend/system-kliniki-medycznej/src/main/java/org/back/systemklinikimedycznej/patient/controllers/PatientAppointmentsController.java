package org.back.systemklinikimedycznej.patient.controllers;


import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.appointment.controllers.dto.UpcomingAppointmentInfo;
import org.back.systemklinikimedycznej.config.util.DateFormatter;
import org.back.systemklinikimedycznej.patient.controllers.dto.PatientPesel;
import org.back.systemklinikimedycznej.patient.services.PatientAppointmentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/patient-appointments")
@RequiredArgsConstructor
public class PatientAppointmentsController {
    private final PatientAppointmentService patientAppointmentService;
    @GetMapping("/next-appointment")
    public ResponseEntity<UpcomingAppointmentInfo> getNextAppointmentInfoForPatient(
            @RequestParam(name="currentDate") @DateTimeFormat(pattern = DateFormatter.DATE_FORMAT) LocalDate currentDate,
            @RequestBody PatientPesel patientPesel
    ) {
        UpcomingAppointmentInfo nextAppointment = patientAppointmentService.findNextAppointmentForPatientWithPesel(patientPesel, currentDate);

        return ResponseEntity.ok(nextAppointment);
    }
}
