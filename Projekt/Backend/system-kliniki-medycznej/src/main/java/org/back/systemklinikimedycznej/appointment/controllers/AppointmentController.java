package org.back.systemklinikimedycznej.appointment.controllers;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.appointment.controllers.dto.AppointmentDto;
import org.back.systemklinikimedycznej.appointment.controllers.dto.AppointmentInfo;
import org.back.systemklinikimedycznej.appointment.mappers.AppointmentMapper;
import org.back.systemklinikimedycznej.appointment.repositories.entities.Appointment;
import org.back.systemklinikimedycznej.appointment.services.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;
    @PostMapping("/schedule-appointment")
    public ResponseEntity<AppointmentInfo> create(@RequestBody AppointmentDto appointmentDto){
        AppointmentInfo scheduledAppointment = AppointmentMapper.APPOINTMENT_MAPPER.mapToAppointmentInfo(appointmentService.createScheduledAppointmentForRegisteredUser(appointmentDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduledAppointment);
    }

}
