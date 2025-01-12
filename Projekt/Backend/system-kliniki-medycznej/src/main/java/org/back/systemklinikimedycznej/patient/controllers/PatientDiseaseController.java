package org.back.systemklinikimedycznej.patient.controllers;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.disease.dto.SearchDisease;
import org.back.systemklinikimedycznej.model.Pagination;
import org.back.systemklinikimedycznej.patient.controllers.dto.CreateHospitalizationRequest;
import org.back.systemklinikimedycznej.patient.controllers.dto.HospitalizationInfo;
import org.back.systemklinikimedycznej.patient.controllers.dto.PatientDiseaseHospitalizationInfo;
import org.back.systemklinikimedycznej.patient.mapper.HospitalizationMapper;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_disease.Hospitalization;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_disease.PatientDisease;
import org.back.systemklinikimedycznej.patient.services.PatientDiseaseService;
import org.back.systemklinikimedycznej.patient.services.HospitalizationService;
import org.back.systemklinikimedycznej.patient.services.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patient-diseases")
@RequiredArgsConstructor
public class PatientDiseaseController {
    private final PatientDiseaseService patientDiseaseService;
    private final PatientService patientService;
    private final HospitalizationService hospitalizationService;
    @GetMapping
    public ResponseEntity<List<PatientDiseaseHospitalizationInfo>> getPagedPatientDiseases(
            @RequestParam("patientCardId") String patientCardId,
            @RequestParam("page") Integer page,
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam(value = "cureStatus",required = false,defaultValue = "ALL") String cureStatus
    ){
        Pagination pagination = new Pagination(page,pageSize);
        List<PatientDiseaseHospitalizationInfo> patientDiseasesHospitalizationInfo = patientDiseaseService.getPagedPatientDiseasesHospitalizationInfo(patientCardId, cureStatus, pagination);

        return ResponseEntity.ok(patientDiseasesHospitalizationInfo);
    }

    @GetMapping("/{id}/hospitalization-history")
    public ResponseEntity<List<HospitalizationInfo>> getPagedHospitalizationHistory(
            @PathVariable("id") Long patientDiseaseId,
            @RequestParam("page") Integer page,
            @RequestParam("pageSize") Integer pageSize
    ){
        PatientDisease patientDisease = patientDiseaseService.findById(patientDiseaseId);
        Pagination pagination = new Pagination(page, pageSize);
        List<HospitalizationInfo> hospitalizationHistory = hospitalizationService.getPagedHospitalizationHistoryForPatientDisease(patientDisease,pagination);

        return ResponseEntity.ok(hospitalizationHistory);
    }

    @PostMapping("/search")
    public ResponseEntity<List<PatientDiseaseHospitalizationInfo>> searchPatientDiseasesHospitalization(
            @RequestParam("page") Integer page,
            @RequestParam("pageSize") Integer pageSize,
            @RequestBody SearchDisease searchDisease,
            @RequestParam("patientCardId") UUID patientCardId
            ){
        Pagination pagination = new Pagination(page,pageSize);
        List<PatientDiseaseHospitalizationInfo> hospitalizationInfos = patientDiseaseService.searchPatientDiseasesHospitalization(patientCardId, searchDisease, pagination);

        return ResponseEntity.ok(hospitalizationInfos);
    }

    @PostMapping("/create")
    public ResponseEntity<HospitalizationInfo> createPatientDiseaseHospitalization(
            @RequestBody CreateHospitalizationRequest hospitalizationRequest
            ){
        PatientDisease patientDisease = patientDiseaseService.findById(hospitalizationRequest.patientDiseaseId());
        Hospitalization hospitalization = hospitalizationService.buildHospitalization(patientDisease, hospitalizationRequest);

        HospitalizationInfo createdHospitalization = HospitalizationMapper.INSTANCE.mapFromEntity(hospitalizationService.save(hospitalization));

        return ResponseEntity.ok(createdHospitalization);
    }
}
