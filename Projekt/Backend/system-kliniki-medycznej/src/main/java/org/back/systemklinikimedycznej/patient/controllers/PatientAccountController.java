package org.back.systemklinikimedycznej.patient.controllers;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.account.dto.AccountData;
import org.back.systemklinikimedycznej.account.mapper.AccountMapper;
import org.back.systemklinikimedycznej.account.services.AccountService;
import org.back.systemklinikimedycznej.patient.repositories.entities.Patient;
import org.back.systemklinikimedycznej.patient.services.PatientAccountService;
import org.back.systemklinikimedycznej.patient.services.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patients/account")
@RequiredArgsConstructor
public class PatientAccountController {
    private final PatientService patientService;
    private final PatientAccountService patientAccountService;
    @GetMapping
    public ResponseEntity<AccountData> getPatientAccount(
            @RequestParam(name = "id") Long patientId
    ){
        Patient patient = patientService.findById(patientId);
        AccountData account = AccountMapper.INSTANCE.mapFromAccountToAccountData(patientAccountService.findAccount(patient));

        return ResponseEntity.ok(account);
    }
}
