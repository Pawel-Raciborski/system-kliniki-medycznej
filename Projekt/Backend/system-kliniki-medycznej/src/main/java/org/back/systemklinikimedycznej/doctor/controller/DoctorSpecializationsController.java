package org.back.systemklinikimedycznej.doctor.controller;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.doctor.controller.dto.DoctorSpecializationDto;
import org.back.systemklinikimedycznej.doctor.mapper.DoctorSpecializationMapper;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.back.systemklinikimedycznej.doctor.repositories.entities.DoctorSpecialization;
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

    @PutMapping()
    public ResponseEntity<DoctorSpecializationDto> update(
            @RequestBody DoctorSpecializationDto doctorSpecialization
    ){
        DoctorSpecialization doctorSpecializationToUpdate = this.doctorSpecializationsService.findById(doctorSpecialization.id());
        DoctorSpecializationDto updatedDoctorSpecialization = DoctorSpecializationMapper.INSTANCE.mapFromEntity(doctorSpecializationsService.update(doctorSpecializationToUpdate,doctorSpecialization));

        return ResponseEntity.ok(updatedDoctorSpecialization);
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
        DoctorSpecialization specializationToRemove = doctorSpecializationsService.findByDoctorAndName(doctor,specializationName);
        DoctorSpecializationDto removedDoctorSpecialization = DoctorSpecializationMapper.INSTANCE.mapFromEntity(
                doctorSpecializationsService.removeDoctorSpecialization(specializationToRemove)
        );

        return ResponseEntity.ok(removedDoctorSpecialization);
    }

    @GetMapping("/specialization-names")
    public ResponseEntity<List<String>> getSpecializationNames(){
        List<String> allSpecializationNames = doctorSpecializationsService.getAllSpecializationNames();

        return ResponseEntity.ok(allSpecializationNames);
    }
}
