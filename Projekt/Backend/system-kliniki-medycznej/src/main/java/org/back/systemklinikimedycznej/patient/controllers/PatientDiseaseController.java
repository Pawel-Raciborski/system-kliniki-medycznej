package org.back.systemklinikimedycznej.patient.controllers;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.model.Pagination;
import org.back.systemklinikimedycznej.patient.repositories.entities.Patient;
import org.back.systemklinikimedycznej.patient.services.PatientDiseaseService;
import org.back.systemklinikimedycznej.patient.services.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient-diseases")
@RequiredArgsConstructor
public class PatientDiseaseController {
    private final PatientDiseaseService patientDiseaseService;
    private final PatientService patientService;
    @GetMapping
    public ResponseEntity<?> getPagedPatientDiseasesHospitalizationHistory(
            @RequestParam("patientId") Long patientId,
            @RequestBody Pagination pagination,
            @RequestParam(value = "cureStatus",required = false,defaultValue = "ALL") String cureStatus
    ){
        Patient patient = patientService.findById(patientId);
        patientDiseaseService.getPagedPatientDiseasesHospitalizationHistory(patient,cureStatus,pagination);

        return ResponseEntity.ok().build();
    }
}
