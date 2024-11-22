package org.back.systemklinikimedycznej.appointment.controllers;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.appointment.controllers.dto.AppointmentDetails;
import org.back.systemklinikimedycznej.appointment.controllers.dto.AppointmentDto;
import org.back.systemklinikimedycznej.appointment.controllers.dto.AppointmentInfo;
import org.back.systemklinikimedycznej.appointment.domain.AppointmentStatus;
import org.back.systemklinikimedycznej.appointment.repositories.entities.Appointment;
import org.back.systemklinikimedycznej.appointment.services.AppointmentService;
import org.back.systemklinikimedycznej.appointment.util.AppointmentManagerUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;
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
}
