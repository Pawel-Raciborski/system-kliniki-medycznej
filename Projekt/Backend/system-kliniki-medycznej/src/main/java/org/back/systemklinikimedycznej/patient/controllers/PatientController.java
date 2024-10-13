package org.back.systemklinikimedycznej.patient.controllers;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.patient.controllers.dto.PatientData;
import org.back.systemklinikimedycznej.patient.controllers.dto.PatientPesel;
import org.back.systemklinikimedycznej.patient.controllers.dto.PatientToRegisterData;
import org.back.systemklinikimedycznej.patient.controllers.dto.Patients;
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
    public ResponseEntity<PatientData> register(
            @RequestBody PatientToRegisterData patientToRegister
    ){
        PatientData patientData = PatientMapper.INSTANCE.mapFromEntity(patientService.registerPatient(patientToRegister));

        return ResponseEntity.status(HttpStatus.CREATED).body(patientData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientData> findById(
            @PathVariable(name = "id") Long id
    ){
        PatientData foundPatient = PatientMapper.INSTANCE.mapFromEntity(patientService.findById(id));

        return ResponseEntity.ok(foundPatient);
    }

    @GetMapping("/all")
    public ResponseEntity<Patients> findAllPaged(
            @RequestParam(name = "page",defaultValue = "0") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize){
        var pagedPatients = patientService.findAllPaged(page,pageSize);
        Patients patients = Patients.builder().patients(pagedPatients.stream().map(PatientMapper.INSTANCE::mapFromEntity).toList())
                .build();

        return ResponseEntity.ok(patients);
    }


    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(
            @RequestBody PatientPesel patientPesel
            ){
        patientService.delete(patientPesel);

        return ResponseEntity.ok().build();
    }

}
