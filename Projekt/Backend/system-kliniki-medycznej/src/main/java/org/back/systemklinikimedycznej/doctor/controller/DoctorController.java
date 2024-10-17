package org.back.systemklinikimedycznej.doctor.controller;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.doctor.controller.dto.DoctorDetails;
import org.back.systemklinikimedycznej.doctor.controller.dto.DoctorDto;
import org.back.systemklinikimedycznej.doctor.controller.dto.RegisterDoctorDetails;
import org.back.systemklinikimedycznej.doctor.controller.dto.DoctorsInfo;
import org.back.systemklinikimedycznej.doctor.mapper.DoctorMapper;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.back.systemklinikimedycznej.doctor.services.DoctorSearchingService;
import org.back.systemklinikimedycznej.doctor.services.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;
    private final DoctorSearchingService doctorInfoService;

    @PostMapping("/create")
    public ResponseEntity<DoctorDto> create(@RequestBody RegisterDoctorDetails doctorToRegisterData) {
        DoctorDto registeredDoctor = DoctorMapper.INSTANCE.mapToDto(
                doctorService.create(doctorToRegisterData)
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(registeredDoctor);
    }

    @GetMapping("/{pwzNumber}")
    public ResponseEntity<DoctorDetails> getDoctor(@PathVariable(name = "pwzNumber") String pwzNumber){
        DoctorDetails doctorDetails = DoctorMapper.INSTANCE.mapToDoctorDetails(doctorService.findByPwzNumber(pwzNumber));
        return ResponseEntity.ok(doctorDetails);
    }

    @PutMapping("/update-pwzNumber")
    public ResponseEntity<String> updateDoctorDetails(
            @RequestParam("oldPwzNumber") String oldPwzNumber,
            @RequestParam("newPwzNumber") String newPwzNumber
    ) {
        String updatedPwzNumber = doctorService.updatePwzNumber(oldPwzNumber, newPwzNumber);
        return ResponseEntity.ok(updatedPwzNumber);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam("pwzNumber") String pwzNumber) {
        doctorService.delete(pwzNumber);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<DoctorsInfo> findAllPaged(
            @RequestParam(name = "page",defaultValue = "0") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(name = "sort", defaultValue = "personalDetails.name,ASC") String sort
    ) {
        DoctorsInfo doctorsInfo = doctorInfoService.findAllPaged(page, pageSize, sort);

        return ResponseEntity.ok(doctorsInfo);
    }

    @GetMapping("/with-specialization")
    public ResponseEntity<DoctorsInfo> findPagedWithSpecialization(
            @RequestParam(name = "specialization") String specializationName,
            @RequestParam(name = "page",defaultValue = "0") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        DoctorsInfo doctorsInfo = doctorInfoService.findPagedWithSpecialization(specializationName, page, pageSize);

        return ResponseEntity.ok(doctorsInfo);
    }
}
