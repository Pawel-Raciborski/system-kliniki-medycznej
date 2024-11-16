package org.back.systemklinikimedycznej.patient.controllers;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.model.Pagination;
import org.back.systemklinikimedycznej.patient.controllers.dto.PatientDiseaseHospitalizationInfo;
import org.back.systemklinikimedycznej.patient.repositories.entities.Patient;
import org.back.systemklinikimedycznej.patient.services.PatientDiseaseService;
import org.back.systemklinikimedycznej.patient.services.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient-diseases")
@RequiredArgsConstructor
public class PatientDiseaseController {
    private final PatientDiseaseService patientDiseaseService;
    private final PatientService patientService;
    @GetMapping
    public ResponseEntity<List<PatientDiseaseHospitalizationInfo>> getPagedPatientDiseasesHospitalizationHistory(
            @RequestParam("patientId") Long patientId,
            @RequestBody Pagination pagination,
            @RequestParam(value = "cureStatus",required = false,defaultValue = "ALL") String cureStatus
    ){
        Patient patient = patientService.findById(patientId);
        List<PatientDiseaseHospitalizationInfo> patientDiseasesHospitalizationInfo = patientDiseaseService.getPagedPatientDiseasesHospitalizationInfo(patient, cureStatus, pagination);

        return ResponseEntity.ok(patientDiseasesHospitalizationInfo);
    }
}
