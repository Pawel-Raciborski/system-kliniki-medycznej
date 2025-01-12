package org.back.systemklinikimedycznej.auth.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.account.repositories.entities.Account;
import org.back.systemklinikimedycznej.account.services.AccountService;
import org.back.systemklinikimedycznej.auth.domain.ApplicationUser;
import org.back.systemklinikimedycznej.auth.domain.LoginData;
import org.back.systemklinikimedycznej.auth.jwt.JwtService;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.back.systemklinikimedycznej.doctor.services.DoctorService;
import org.back.systemklinikimedycznej.patient.repositories.entities.Patient;
import org.back.systemklinikimedycznej.patient.services.PatientService;
import org.back.systemklinikimedycznej.receptionist.repositories.entities.Receptionist;
import org.back.systemklinikimedycznej.receptionist.services.ReceptionistService;
import org.back.systemklinikimedycznej.role.controller.dto.RoleDto;
import org.back.systemklinikimedycznej.role.mapper.RoleMapper;
import org.back.systemklinikimedycznej.role.repository.entities.AccountRole;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExtraPropertiesProviderService {
    private final DoctorService doctorService;
    private final ReceptionistService receptionistService;
    private final PatientService patientService;
    private final JwtService jwtService;

    public LoginData generateToken(ApplicationUser user) {
        Map<String,Object> map = new HashMap<>();
        map.put("username", user.getUsername());
        map.put("email", user.getAccount().getEmail());
        map.put("roles", user.getAccount().getAccountRoles().stream().map(r -> r.getRole().getName()).toList());
        map.put("accountId", user.getAccount().getId());
        user.getAccount().getAccountRoles().stream()
                .map(AccountRole::getRole)
                .map(RoleMapper.INSTANCE::mapFromEntity)
                .forEach(r -> addData(map,r,user.getAccount()));

        String token = jwtService.generateToken(map,user);

        return LoginData.builder()
                .token(token)
                .build();
    }

    private void addData(Map<String, Object> data, RoleDto role, Account account) {
        switch (role.name()) {
            case "DOCTOR" -> {
                Doctor doctor = doctorService.findByAccount(account);
                data.put("doctorId",doctor.getId());
            }
            case "RECEPTIONIST" -> {
                Receptionist receptionist = receptionistService.findByAccount(account);
                data.put("receptionistId",receptionist.getId());
            }
            case "PATIENT" -> {
                Patient patient = patientService.findByAccount(account);
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
