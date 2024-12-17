package org.back.systemklinikimedycznej.patient.controllers;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.patient.controllers.dto.CollectedPatientData;
import org.back.systemklinikimedycznej.patient.controllers.dto.PatientDetailsDto;
import org.back.systemklinikimedycznej.patient.controllers.dto.PatientPesel;
import org.back.systemklinikimedycznej.patient.mapper.PatientDetailsMapper;
import org.back.systemklinikimedycznej.patient.repositories.entities.PatientDetails;
import org.back.systemklinikimedycznej.patient.services.PatientDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients-details")
@RequiredArgsConstructor
public class PatientDetailsController {
    private final PatientDetailsService patientDetailsService;

    @PostMapping("/create")
    public ResponseEntity<PatientDetailsDto> create(
            @RequestBody PatientDetailsDto patientDetailsToCreate,
            @RequestParam(name = "pesel") String pesel
    ) {
        PatientDetailsDto patientDetailsDto = PatientDetailsMapper.INSTANCE.mapFromEntity(patientDetailsService.create(patientDetailsToCreate, pesel));

        return ResponseEntity.status(HttpStatus.CREATED).body(patientDetailsDto);
    }

    @GetMapping
    public ResponseEntity<PatientDetailsDto> getPatientDetails(
            @RequestParam("pesel") String pesel
    ) {
        PatientDetailsDto patientDetailsDto = PatientDetailsMapper.INSTANCE.mapFromEntity(patientDetailsService.findPatientDetailsByPesel(pesel));
        return ResponseEntity.ok(patientDetailsDto);
    }

    @PutMapping("/update")
    public ResponseEntity<PatientDetailsDto> updatePatientDetails(
            @RequestBody PatientDetailsDto collectedPatientData
    ) {
        PatientDetails patientDetailsToUpdate = patientDetailsService.findById(collectedPatientData.id());
        PatientDetailsDto updatedPatientDetails = PatientDetailsMapper.INSTANCE.mapFromEntity(
                patientDetailsService.update(patientDetailsToUpdate, collectedPatientData)
        );

        return ResponseEntity.ok(updatedPatientDetails);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<PatientDetailsDto> delete(
            @RequestBody PatientPesel patientPesel
    ) {
        PatientDetailsDto removedPatientDetails = PatientDetailsMapper.INSTANCE.mapFromEntity(patientDetailsService.delete(patientPesel.pesel()));

        return ResponseEntity.ok(removedPatientDetails);
    }
}
