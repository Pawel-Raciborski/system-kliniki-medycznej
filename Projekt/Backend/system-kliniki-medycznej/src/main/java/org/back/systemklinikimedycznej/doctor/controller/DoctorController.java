package org.back.systemklinikimedycznej.doctor.controller;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.doctor.controller.dto.DoctorDto;
import org.back.systemklinikimedycznej.doctor.controller.dto.RegisterDoctorForm;
import org.back.systemklinikimedycznej.doctor.mapper.DoctorMapper;
import org.back.systemklinikimedycznej.doctor.services.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;
    @PostMapping("/create")
    public ResponseEntity<DoctorDto> create(@RequestBody RegisterDoctorForm registerDoctorForm){
        DoctorDto registeredDoctor = DoctorMapper.INSTANCE.mapToDto(
                doctorService.create(registerDoctorForm)
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(registeredDoctor);
    }
}
