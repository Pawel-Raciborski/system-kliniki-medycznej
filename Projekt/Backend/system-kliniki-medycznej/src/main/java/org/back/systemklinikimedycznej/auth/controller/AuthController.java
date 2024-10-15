package org.back.systemklinikimedycznej.auth.controller;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.auth.controller.dto.Credentials;
import org.back.systemklinikimedycznej.auth.controller.dto.LoginDataDto;
import org.back.systemklinikimedycznej.auth.mapper.LoginDataMapper;
import org.back.systemklinikimedycznej.auth.services.AuthService;
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
    @PostMapping("/login")
    public ResponseEntity<LoginDataDto> login(
            @RequestBody Credentials credentials
    ){
           LoginDataDto loginData = LoginDataMapper.INSTANCE.mapFromDomain(authService.processLogin(credentials));

           return ResponseEntity.ok(loginData);
    }
}
