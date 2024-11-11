package org.back.systemklinikimedycznej.auth.controller;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.auth.controller.dto.Credentials;
import org.back.systemklinikimedycznej.auth.controller.dto.LoginDataDto;
import org.back.systemklinikimedycznej.auth.domain.LoginData;
import org.back.systemklinikimedycznej.auth.mapper.LoginDataMapper;
import org.back.systemklinikimedycznej.auth.services.AuthService;
import org.back.systemklinikimedycznej.auth.services.ExtraPropertiesProviderService;
import org.back.systemklinikimedycznej.role.repository.entities.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final ExtraPropertiesProviderService extraPropertiesProviderService;

    @PostMapping("/login")
    public ResponseEntity<LoginData> login(
            @RequestBody Credentials credentials
    ) {
        LoginData loginData = authService.processLogin(credentials);
        extraPropertiesProviderService.addExtraData(loginData.data());
        return ResponseEntity.ok(loginData);
    }
}
