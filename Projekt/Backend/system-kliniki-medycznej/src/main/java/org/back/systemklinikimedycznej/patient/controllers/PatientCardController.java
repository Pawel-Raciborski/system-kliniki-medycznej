package org.back.systemklinikimedycznej.patient.controllers;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.patient.controllers.dto.SummaryResponse;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_card.PatientCard;
import org.back.systemklinikimedycznej.patient.services.PatientCardService;
import org.back.systemklinikimedycznej.patient.services.PatientSummaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/patient-card")
@RequiredArgsConstructor
public class PatientCardController {
    private final PatientCardService patientCardService;
    private final PatientSummaryService patientSummaryService;

    @GetMapping("/{cardId}/summary")
    public ResponseEntity<SummaryResponse> getPatientCardSummary(
            @PathVariable("cardId") UUID cardId
    ){
        PatientCard patientCard = patientCardService.findById(cardId);
        SummaryResponse summaryResponse = patientSummaryService.createSummary(patientCard);

        return ResponseEntity.ok(summaryResponse);
    }
}
