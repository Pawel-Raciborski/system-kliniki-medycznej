package org.back.systemklinikimedycznej.patient.controllers;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.patient.controllers.dto.CollectedPatientData;
import org.back.systemklinikimedycznej.patient.controllers.dto.PatientDetailsDto;
import org.back.systemklinikimedycznej.patient.controllers.dto.PatientPesel;
import org.back.systemklinikimedycznej.patient.mapper.PatientDetailsMapper;
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
    public ResponseEntity<PatientDetailsDto> create(@RequestBody CollectedPatientData collectedPatientData){
        PatientDetailsDto patientDetailsDto = PatientDetailsMapper.INSTANCE.mapFromEntity(patientDetailsService.create(collectedPatientData));

        return ResponseEntity.status(HttpStatus.CREATED).body(patientDetailsDto);
    }

    @GetMapping
    public ResponseEntity<PatientDetailsDto> getPatientDetails(
            @RequestBody PatientPesel patientPesel
    ){
        PatientDetailsDto patientDetailsDto = PatientDetailsMapper.INSTANCE.mapFromEntity(patientDetailsService.findPatientDetailsByPesel(patientPesel.pesel()));
        return ResponseEntity.ok(patientDetailsDto);
    }

    @PutMapping("/update")
    public ResponseEntity<PatientDetailsDto> updatePatientDetails(
            @RequestBody CollectedPatientData collectedPatientData
    ){
        PatientDetailsDto updatedPatientDetails = PatientDetailsMapper.INSTANCE.mapFromEntity(
                patientDetailsService.update(collectedPatientData)
        );

        return ResponseEntity.ok(updatedPatientDetails);
    }
}
