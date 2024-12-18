package org.back.systemklinikimedycznej.appointment.controllers;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.appointment.controllers.dto.*;
import org.back.systemklinikimedycznej.appointment.domain.AppointmentStatus;
import org.back.systemklinikimedycznej.appointment.repositories.entities.Appointment;
import org.back.systemklinikimedycznej.appointment.services.AppointmentService;
import org.back.systemklinikimedycznej.appointment.util.AppointmentManagerUtil;
import org.back.systemklinikimedycznej.disease.services.DiseaseService;
import org.back.systemklinikimedycznej.patient.services.PatientDiseaseService;
import org.back.systemklinikimedycznej.prescription.services.PrescriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final PrescriptionService prescriptionService;
    private final PatientDiseaseService patientDiseaseService;
    @PostMapping("/schedule-appointment")
    public ResponseEntity<AppointmentInfo> create(@RequestBody AppointmentDto appointmentDto){
        Appointment createdAppointment = appointmentService.createScheduledAppointment(appointmentDto);
        AppointmentInfo scheduledAppointment = AppointmentManagerUtil.buildAppointmentInfo(createdAppointment);
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduledAppointment);
    }

    @PutMapping("/update-status")
    public ResponseEntity<AppointmentInfo> updateStatus(
            @RequestParam(name="id") UUID appointmentId,
            @RequestParam(name = "newStatus") String newStatus
            ){
        Appointment appointmentToUpdate = appointmentService.findById(appointmentId);
        AppointmentInfo updatedAppointment = AppointmentManagerUtil.buildAppointmentInfo(
                appointmentService.updateStatus(appointmentToUpdate, AppointmentStatus.valueOf(newStatus))
        );

        return ResponseEntity.ok(updatedAppointment);
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<AppointmentDetails> getAppointmentDetails(
            @PathVariable("id") UUID appointmentId
    ){
        Appointment appointment = appointmentService.findById(appointmentId);
        AppointmentDetails appointmentDetails = appointmentService.getAppointmentDetails(appointment);

        return ResponseEntity.ok(appointmentDetails);
    }

    @PutMapping("/finish")
    public ResponseEntity<Void> finishAppointment(
            @RequestBody FinishAppointmentRequest appointmentRequest
            ){
        Appointment appointmentToFinish = appointmentService.findById(appointmentRequest.appointmentId());
        appointmentToFinish.setDiagnosis(appointmentToFinish.getDiagnosis());
        appointmentRequest.appointmentPrescriptions().forEach(prescriptionService::create);
        appointmentRequest.patientDiseases().forEach(patientDiseaseService::create);
        appointmentService.finishAppointment(appointmentToFinish);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/available-statuses")
    public ResponseEntity<List<AppointmentStatusDto>> getAvailableStatuses(){
        var statuses = Arrays.stream(AppointmentStatus.values()).map(
                appointmentStatus -> new AppointmentStatusDto(appointmentStatus.name(),appointmentStatus.getAppointmentStatusName())
        ).toList();

        return ResponseEntity.ok(statuses);
    }
}
