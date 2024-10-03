package org.back.systemklinikimedycznej.doctor.controller;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.doctor.controller.dto.DoctorDto;
import org.back.systemklinikimedycznej.doctor.controller.dto.DoctorFormDto;
import org.back.systemklinikimedycznej.doctor.mapper.DoctorMapper;
import org.back.systemklinikimedycznej.doctor.services.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;
    @PostMapping("/create")
    public ResponseEntity<DoctorDto> create(@RequestBody DoctorFormDto doctorFormDto){
        DoctorDto registeredDoctor = DoctorMapper.INSTANCE.mapToDto(
                doctorService.create(doctorFormDto)
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(registeredDoctor);
    }

    @PutMapping("/update-pwzNumber")
    public ResponseEntity<String> updateDoctorDetails(
            @RequestParam("oldPwzNumber") String oldPwzNumber,
            @RequestParam("newPwzNumber") String newPwzNumber
    ) {
        String updatedPwzNumber = doctorService.updatePwzNumber(oldPwzNumber,newPwzNumber);
        return ResponseEntity.ok(updatedPwzNumber);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam("pwzNumber") String pwzNumber){
        doctorService.delete(pwzNumber);

        return ResponseEntity.ok().build();
    }
}
