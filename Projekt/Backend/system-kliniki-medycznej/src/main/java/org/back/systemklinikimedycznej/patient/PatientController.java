package org.back.systemklinikimedycznej.patient;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.patient.controllers.dto.PatientToRegisterData;
import org.back.systemklinikimedycznej.patient.controllers.dto.RegisteredPatientData;
import org.back.systemklinikimedycznej.patient.mapper.PatientMapper;
import org.back.systemklinikimedycznej.patient.services.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;
    @PostMapping("/register")
    public ResponseEntity<RegisteredPatientData> register(
            @RequestBody PatientToRegisterData patientToRegister
    ){
        RegisteredPatientData registeredPatientData = PatientMapper.INSTANCE.mapFromEntity(patientService.registerPatient(patientToRegister));

        return ResponseEntity.status(HttpStatus.CREATED).body(registeredPatientData);
    }
}
