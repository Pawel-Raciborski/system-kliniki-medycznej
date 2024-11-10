package org.back.systemklinikimedycznej.prescription.controller;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.prescription.dto.CreatePrescriptionForm;
import org.back.systemklinikimedycznej.prescription.dto.PrescriptionDetails;
import org.back.systemklinikimedycznej.prescription.repositories.entities.Prescription;
import org.back.systemklinikimedycznej.prescription.services.PrescriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/prescriptions")
@RequiredArgsConstructor
public class PrescriptionController {
    private final PrescriptionService prescriptionService;

    @PostMapping("/create")
    public ResponseEntity<Prescription> create(
            @RequestBody CreatePrescriptionForm createPrescriptionForm
    ) {
        Prescription createdPrescription = prescriptionService.create(createPrescriptionForm);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdPrescription);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrescriptionDetails> getPrescriptionDetails(@PathVariable("id") UUID id){
        PrescriptionDetails prescriptionDetails = prescriptionService.getPrescriptionDetails(id);

        return ResponseEntity.ok(prescriptionDetails);
    }
}
