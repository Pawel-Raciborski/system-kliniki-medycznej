package org.back.systemklinikimedycznej.patient.controllers;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.account.dto.AccountDto;
import org.back.systemklinikimedycznej.account.dto.AccountInfo;
import org.back.systemklinikimedycznej.account.mapper.AccountMapper;
import org.back.systemklinikimedycznej.account.repositories.entities.Account;
import org.back.systemklinikimedycznej.patient.controllers.dto.*;
import org.back.systemklinikimedycznej.patient.mapper.PatientMapper;
import org.back.systemklinikimedycznej.patient.repositories.entities.Patient;
import org.back.systemklinikimedycznej.patient.services.PatientService;
import org.back.systemklinikimedycznej.receptionist.controller.dto.RegisterReceptionistForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;
    @PostMapping("/register")
    public ResponseEntity<PatientData> register(
            @RequestBody PatientToRegisterData patientToRegister
    ){
        PatientData patientData = PatientMapper.INSTANCE.mapFromEntity(patientService.registerPatient(patientToRegister));

        return ResponseEntity.status(HttpStatus.CREATED).body(patientData);
    }

    @PostMapping("/create-account")
    public ResponseEntity<RegisteredPatientAccount> createAccountForPatient(
            @RequestBody PatientToRegisterCredentials patientToRegisterCredentials
    ){
        Patient patient = patientService.findByPesel(patientToRegisterCredentials.pesel());
        RegisteredPatientAccount accountForPatient = patientService.createAccountForPatient(patientToRegisterCredentials,patient);

        return ResponseEntity.ok(accountForPatient);
    }

    @GetMapping("/{id}/account")
    public ResponseEntity<AccountInfo> getPatientAccount(
            @PathVariable("id") Long patientId
    ){
        Patient patient = patientService.findById(patientId);
        AccountInfo account = AccountMapper.INSTANCE.mapFromAccountToAccountInfo(patient.getAccount());

        return ResponseEntity.ok(account);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientData> findById(
            @PathVariable(name = "id") Long id
    ){
        PatientData foundPatient = PatientMapper.INSTANCE.mapFromEntity(patientService.findById(id));

        return ResponseEntity.ok(foundPatient);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PatientData>> findAllPaged(
            @RequestParam(name = "page",defaultValue = "0") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize){
        var pagedPatients = patientService.findAllPaged(page,pageSize);
        List<PatientData> patients = pagedPatients.stream().map(PatientMapper.INSTANCE::mapFromEntity).toList();

        return ResponseEntity.ok(patients);
    }


    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(
            @RequestBody PatientPesel patientPesel
            ){
        patientService.delete(patientPesel);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<PatientData>> searchPatients(
            @RequestParam("pesel") String pesel
    ){
        List<PatientData> patients = patientService.searchPatientsWithPesel(pesel).stream()
                .map(PatientMapper.INSTANCE::mapFromEntity)
                .toList();

        return ResponseEntity.ok(patients);
    }

}
