package org.back.systemklinikimedycznej.patient.controllers;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.patient.controllers.dto.HospitalizationInfo;
import org.back.systemklinikimedycznej.patient.mapper.HospitalizationMapper;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_disease.Hospitalization;
import org.back.systemklinikimedycznej.patient.services.HospitalizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hospitalization")
@RequiredArgsConstructor
public class HospitalizationController {
    private final HospitalizationService hospitalizationService;

    @PutMapping("/update")
    public ResponseEntity<HospitalizationInfo> updateHospitalization(
            @RequestBody HospitalizationInfo updatedHospitalizationData
    ){
        Hospitalization hospitalizationToUpdate = hospitalizationService.findById(updatedHospitalizationData.id());
        HospitalizationInfo updatedHospitalization = HospitalizationMapper.INSTANCE.mapFromEntity(
                hospitalizationService.updateDiseaseHospitalization(hospitalizationToUpdate,updatedHospitalizationData)
        );

        return ResponseEntity.ok(updatedHospitalization);
    }

}
