package org.back.systemklinikimedycznej.prescription.controller;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.model.Pagination;
import org.back.systemklinikimedycznej.prescription.controller.dto.PrescriptionInfo;
import org.back.systemklinikimedycznej.prescription.dto.CreatePrescriptionForm;
import org.back.systemklinikimedycznej.prescription.dto.PrescriptionDetails;
import org.back.systemklinikimedycznej.prescription.repositories.entities.Prescription;
import org.back.systemklinikimedycznej.prescription.services.PrescriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/prescriptions")
@RequiredArgsConstructor
public class PrescriptionController {
    private final PrescriptionService prescriptionService;

    @PostMapping("/create")
    public ResponseEntity<PrescriptionInfo> create(
            @RequestBody CreatePrescriptionForm createPrescriptionForm
    ) {
        PrescriptionInfo createdPrescription = prescriptionService.create(createPrescriptionForm);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdPrescription);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrescriptionDetails> getPrescriptionDetails(@PathVariable("id") UUID id){
        PrescriptionDetails prescriptionDetails = prescriptionService.getPrescriptionDetails(id);

        return ResponseEntity.ok(prescriptionDetails);
    }

    @GetMapping
    public ResponseEntity<List<PrescriptionInfo>> getPatientPrescriptions(
            @RequestParam("patientId") Long patientId,
            @RequestBody Pagination pagination
            ){
        List<PrescriptionInfo> patientPrescriptions = prescriptionService.findPatientPrescriptions(patientId, pagination);
        return ResponseEntity.ok(patientPrescriptions);
    }
}
