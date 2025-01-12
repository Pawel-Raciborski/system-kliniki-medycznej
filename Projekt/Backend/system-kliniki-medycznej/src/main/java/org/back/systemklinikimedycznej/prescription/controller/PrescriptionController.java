package org.back.systemklinikimedycznej.prescription.controller;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.model.Pagination;
import org.back.systemklinikimedycznej.prescription.controller.dto.PrescriptionInfo;
import org.back.systemklinikimedycznej.prescription.controller.dto.CreatePrescriptionRequest;
import org.back.systemklinikimedycznej.prescription.controller.dto.PrescriptionDetails;
import org.back.systemklinikimedycznej.prescription.services.PrescriptionService;
import org.back.systemklinikimedycznej.prescription.util.PrescriptionManagerUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/prescriptions")
@RequiredArgsConstructor
public class PrescriptionController {
    private final PrescriptionService prescriptionService;

    @PostMapping("/create")
    public ResponseEntity<PrescriptionInfo> create(
            @RequestBody CreatePrescriptionRequest createPrescriptionRequest
    ) {
        PrescriptionInfo createdPrescription = prescriptionService.create(createPrescriptionRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdPrescription);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrescriptionDetails> getPrescriptionDetails(@PathVariable("id") UUID id){
        PrescriptionDetails prescriptionDetails = prescriptionService.getPrescriptionDetails(id);

        return ResponseEntity.ok(prescriptionDetails);
    }

    @GetMapping
    public ResponseEntity<List<PrescriptionInfo>> getPatientPrescriptions(
            @RequestParam(value = "patientId", required = false) Long patientId,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "pageSize") Integer pageSize
            ){
        Pagination pagination = new Pagination(page,pageSize);

        if(Objects.isNull(patientId)){
            List<PrescriptionInfo> prescriptions = prescriptionService.findAllPaged(pagination).stream()
                    .map(PrescriptionManagerUtil::buildPrescriptionInfo)
                    .toList();

            return ResponseEntity.ok(prescriptions);
        }

        List<PrescriptionInfo> patientPrescriptions = prescriptionService.findPatientPrescriptions(patientId, pagination);
        return ResponseEntity.ok(patientPrescriptions);
    }
}
