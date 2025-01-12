package org.back.systemklinikimedycznej.auth.controller;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.auth.controller.dto.Credentials;
import org.back.systemklinikimedycznej.auth.domain.ApplicationUser;
import org.back.systemklinikimedycznej.auth.domain.LoginData;
import org.back.systemklinikimedycznej.auth.services.AuthService;
import org.back.systemklinikimedycznej.auth.services.ExtraPropertiesProviderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        ApplicationUser applicationUser = authService.processLogin(credentials);
        LoginData loginData = extraPropertiesProviderService.generateToken(applicationUser);
        return ResponseEntity.ok(loginData);
    }
}
