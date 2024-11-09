package org.back.systemklinikimedycznej.doctor.controller;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.doctor.controller.dto.DoctorInfo;
import org.back.systemklinikimedycznej.doctor.mapper.DoctorMapper;
import org.back.systemklinikimedycznej.doctor.services.DoctorAppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctor-appointment")
@RequiredArgsConstructor
public class DoctorAppointmentController {
    private final DoctorAppointmentService doctorAppointmentService;

    @GetMapping("/details")
    public ResponseEntity<DoctorInfo> getDoctorAppointmentInfo(
            @RequestParam(name = "appointmentId") String appointmentId
    ){
        DoctorInfo doctor = DoctorMapper.INSTANCE.mapToDoctorInfo(doctorAppointmentService.getDoctorAppointmentInfo(appointmentId));

        return ResponseEntity.ok(doctor);
    }
}
