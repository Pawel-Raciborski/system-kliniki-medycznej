package org.back.systemklinikimedycznej.auth.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.account.repositories.entities.Account;
import org.back.systemklinikimedycznej.account.services.AccountService;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.back.systemklinikimedycznej.patient.repositories.entities.Patient;
import org.back.systemklinikimedycznej.patient.services.PatientAccountService;
import org.back.systemklinikimedycznej.receptionist.repositories.entities.Receptionist;
import org.back.systemklinikimedycznej.role.controller.dto.RoleDto;
import org.back.systemklinikimedycznej.role.repository.entities.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExtraPropertiesProviderService {
    private final AccountService accountService;

    public void addExtraData(Map<String, Object> data) {
        Account account = accountService.findByUsername((String) data.get("username"));

        List<RoleDto> roles = extractRoles(data);

        roles.forEach(role -> addData(data, role, account));

    }

    private void addData(Map<String, Object> data, RoleDto role, Account account) {
        switch (role.name()) {
            case "DOCTOR" -> {
                Doctor doctor = account.getDoctor();
                data.put("doctorId",doctor.getId());
            }
            case "RECEPTIONIST" -> {
                Receptionist receptionist = account.getReceptionist();
                data.put("receptionistId",receptionist.getId());
            }
            case "PATIENT" -> {
                Patient patient = account.getPatient();
                data.put("patientId",patient.getId());
            }
        }

    }

    private List<RoleDto> extractRoles(Map<String, Object> data) {
        Object rolesObj = data.get("roles");

        if (rolesObj instanceof List<?> r) {
            return (List<RoleDto>) r;
        }

        return List.of();
    }
}
