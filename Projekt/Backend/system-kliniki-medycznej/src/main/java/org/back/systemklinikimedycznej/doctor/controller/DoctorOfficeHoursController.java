package org.back.systemklinikimedycznej.doctor.controller;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.config.util.DateFormatter;
import org.back.systemklinikimedycznej.doctor.controller.dto.AvailableOfficeHours;
import org.back.systemklinikimedycznej.doctor.controller.dto.OfficeHoursDto;
import org.back.systemklinikimedycznej.doctor.mapper.OfficeHoursMapper;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.back.systemklinikimedycznej.doctor.services.DoctorOfficeHoursService;
import org.back.systemklinikimedycznej.doctor.services.DoctorService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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
            @RequestBody OfficeHoursDto updatedOfficeHours
    ) {
        updatedOfficeHours = OfficeHoursMapper.INSTANCE.mapFromEntity(doctorOfficeHoursService.update(updatedOfficeHours));

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

    @GetMapping("/available-working-hours")
    public ResponseEntity<List<LocalTime>> getDoctorAvailableHours(
            @RequestParam(name="pwzNumber") String doctorPwzNumber,
            @RequestParam(name="date") @DateTimeFormat(pattern=DateFormatter.DATE_FORMAT) LocalDate appointmentDate
            ){
        Doctor doctor = doctorService.findByPwzNumber(doctorPwzNumber);
        AvailableOfficeHours availableOfficeHours = doctorOfficeHoursService.findAvailableHoursInGivenDayForDoctor(doctor, appointmentDate);

        return ResponseEntity.ok(availableOfficeHours.officeHours());
    }

    @GetMapping("/doctor-working-days")
    public ResponseEntity<List<Integer>> findDoctorWorkingDays(
            @RequestParam("pwzNumber") String doctorPwzNumber
    ){
        Doctor doctor = doctorService.findByPwzNumber(doctorPwzNumber);
        List<Integer> workingDays = doctorOfficeHoursService.findWorkingDays(doctor);

        return ResponseEntity.ok(workingDays);
    }
}
