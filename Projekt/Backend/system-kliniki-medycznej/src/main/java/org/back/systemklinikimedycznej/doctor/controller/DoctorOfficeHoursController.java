package org.back.systemklinikimedycznej.doctor.controller;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.doctor.controller.dto.OfficeHoursDto;
import org.back.systemklinikimedycznej.doctor.mapper.OfficeHoursMapper;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.back.systemklinikimedycznej.doctor.services.DoctorOfficeHoursService;
import org.back.systemklinikimedycznej.doctor.services.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;

@RestController
@RequestMapping("/doctor/office-hours")
@RequiredArgsConstructor
public class DoctorOfficeHoursController {
    private final DoctorService doctorService;
    private final DoctorOfficeHoursService doctorOfficeHoursService;

    @PostMapping("/add")
    public ResponseEntity<OfficeHoursDto> add(
            @RequestParam(name = "pwzNumber") String doctorPwzNumber,
            @RequestBody OfficeHoursDto officeHoursDto
    ) {
        Doctor doctorToAddOfficeHours = doctorService.findByPwzNumber(doctorPwzNumber);
        OfficeHoursDto createdDoctorOfficeHours = OfficeHoursMapper.INSTANCE.mapFromEntity(doctorOfficeHoursService.add(doctorToAddOfficeHours, officeHoursDto));

        return ResponseEntity.status(HttpStatus.CREATED).body(createdDoctorOfficeHours);
    }

    @PutMapping("/update")
    public ResponseEntity<OfficeHoursDto> update(
            @RequestParam(name = "pwzNumber") String doctorPwzNumber,
            @RequestBody OfficeHoursDto updatedOfficeHours
    ) {
        Doctor doctorToUpdateOfficeHours = doctorService.findByPwzNumber(doctorPwzNumber);
        updatedOfficeHours = OfficeHoursMapper.INSTANCE.mapFromEntity(doctorOfficeHoursService.update(doctorToUpdateOfficeHours, updatedOfficeHours));

        return ResponseEntity.ok(updatedOfficeHours);
    }

    @GetMapping
    public ResponseEntity<OfficeHoursDto> getHoursForDoctor(
            @RequestParam(name = "day") String day,
            @RequestParam(name = "pwzNumber") String doctorPwzNumber
    ) {
        Doctor doctor = doctorService.findByPwzNumber(doctorPwzNumber);
        OfficeHoursDto foundDoctorOfficeHours = OfficeHoursMapper.INSTANCE.mapFromEntity(
                doctorOfficeHoursService.findOfficeHoursForDoctorForDay(doctor, DayOfWeek.valueOf(day.toUpperCase()))
        );

        return ResponseEntity.ok(foundDoctorOfficeHours);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(
            @RequestParam(name = "pwzNumber") String doctorPwzNumber,
            @RequestParam(name = "day") String day
    ) {
        Doctor doctorForRemoveOfficeHours = doctorService.findByPwzNumber(doctorPwzNumber);
        doctorOfficeHoursService.delete(doctorForRemoveOfficeHours, DayOfWeek.valueOf(day));

        return ResponseEntity.ok().build();
    }
}
