package org.back.systemklinikimedycznej.doctor.controller;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.doctor.controller.dto.DoctorSpecializationDto;
import org.back.systemklinikimedycznej.doctor.mapper.DoctorSpecializationMapper;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.back.systemklinikimedycznej.doctor.services.DoctorService;
import org.back.systemklinikimedycznej.doctor.services.DoctorSpecializationsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor-specializations")
@RequiredArgsConstructor
public class DoctorSpecializationsController {
    private final DoctorSpecializationsService doctorSpecializationsService;
    private final DoctorService doctorService;
    @PostMapping("/add")
    public ResponseEntity<List<DoctorSpecializationDto>> add(
            @RequestParam("pwzNumber") String pwzNumber,
            @RequestBody List<DoctorSpecializationDto> doctorSpecializations
            ){
        Doctor doctor = doctorService.findByPwzNumber(pwzNumber);

        List<DoctorSpecializationDto> createdSpecializations = doctorSpecializationsService.addSpecializationToDoctor(doctor,doctorSpecializations)
                .stream()
                .map(DoctorSpecializationMapper.INSTANCE::mapFromEntity)
                .toList();

        return ResponseEntity.status(HttpStatus.CREATED).body(createdSpecializations);
    }

    @GetMapping
    public ResponseEntity<List<DoctorSpecializationDto>> getDoctorSpecializations(@RequestParam("pwzNumber") String pwzNumber){
        Doctor doctor = doctorService.findByPwzNumber(pwzNumber);
        List<DoctorSpecializationDto> doctorSpecializations = doctorSpecializationsService.findAllSpecializationsForDoctor(doctor)
                .stream()
                .map(DoctorSpecializationMapper.INSTANCE::mapFromEntity)
                .toList();

        return ResponseEntity.ok(doctorSpecializations);
    }

    @DeleteMapping
    public ResponseEntity<DoctorSpecializationDto> removeSpecialization(
            @RequestParam(name = "pwzNumber") String pwzNumber,
            @RequestParam(name="specializationName") String specializationName
    ){
        Doctor doctor = doctorService.findByPwzNumber(pwzNumber);

        DoctorSpecializationDto removedDoctorSpecialization = DoctorSpecializationMapper.INSTANCE.mapFromEntity(
                doctorSpecializationsService.removeDoctorSpecialization(doctor,specializationName)
        );

        return ResponseEntity.ok(removedDoctorSpecialization);
    }
}
