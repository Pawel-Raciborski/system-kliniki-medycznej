package org.back.systemklinikimedycznej.doctor.controller;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.doctor.controller.dto.*;
import org.back.systemklinikimedycznej.doctor.domain.DoctorSearchParams;
import org.back.systemklinikimedycznej.doctor.mapper.DoctorMapper;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.back.systemklinikimedycznej.doctor.services.DoctorSearchingService;
import org.back.systemklinikimedycznej.doctor.services.DoctorService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;
    private final DoctorSearchingService doctorInfoService;

    @PostMapping("/create")
    public ResponseEntity<DoctorInfo> create(
            @RequestPart(name = "profileImage", required = false) MultipartFile profileImage,
            @RequestPart(name = "doctorToRegisterData") RegisterDoctorDetails doctorToRegisterData
    ) {
        DoctorInfo registeredDoctor = DoctorMapper.INSTANCE.mapToDoctorInfo(
                doctorService.create(doctorToRegisterData, profileImage)
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(registeredDoctor);
    }

    @GetMapping("/{pwzNumber}")
    public ResponseEntity<DoctorDetails> getDoctor(@PathVariable(name = "pwzNumber") String pwzNumber) {
        DoctorDetails doctorDetails = DoctorMapper.INSTANCE.mapToDoctorDetails(doctorService.findByPwzNumber(pwzNumber));
        return ResponseEntity.ok(doctorDetails);
    }

    @GetMapping("/{pwzNumber}/{fileName}")
    public ResponseEntity<Resource> getProfileImage(
            @PathVariable(name = "pwzNumber") String pwzNumber,
            @PathVariable(name = "fileName") String fileName
    ) {
        Doctor doctor = doctorService.findByPwzNumber(pwzNumber);
        Resource file = doctorService.getFile(doctor,fileName);


        return ResponseEntity.status(HttpStatus.OK)
                .headers(httpHeaders -> httpHeaders.add(HttpHeaders.CONTENT_TYPE,"image/webp"))
                .body(file);
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
    public ResponseEntity<List<DoctorInfo>> findAllPaged(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(name = "sort", defaultValue = "personalDetails.name,ASC") String sort
    ) {
        DoctorsInfo doctorsInfo = doctorInfoService.findAllPaged(page, pageSize, sort);

        return ResponseEntity.ok(doctorsInfo.doctors());
    }

    @GetMapping("/with-specialization")
    public ResponseEntity<DoctorsInfo> findPagedWithSpecialization(
            @RequestParam(name = "specialization") String specializationName,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        DoctorsInfo doctorsInfo = doctorInfoService.findPagedWithSpecialization(specializationName, page, pageSize);

        return ResponseEntity.ok(doctorsInfo);
    }

    @PostMapping("/search")
    public ResponseEntity<List<DoctorInfo>> searchForDoctors(
            @RequestBody DoctorSearchParams doctorSearchParams
            ){
        List<DoctorInfo> doctors = this.doctorService.searchForDoctors(doctorSearchParams);

        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<DoctorDetails> findById(
            @PathVariable("id") Long doctorId
    ){
        DoctorDetails doctor = DoctorMapper.INSTANCE.mapToDoctorDetails(doctorService.findById(doctorId));

        return ResponseEntity.ok(doctor);
    }
}
