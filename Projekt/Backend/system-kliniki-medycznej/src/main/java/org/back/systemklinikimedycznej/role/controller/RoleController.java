package org.back.systemklinikimedycznej.role.controller;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.role.dto.RoleCreateDto;
import org.back.systemklinikimedycznej.role.mapper.RoleMapper;
import org.back.systemklinikimedycznej.role.services.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;
    @PostMapping("/create")
    public ResponseEntity<RoleCreateDto> create(RoleCreateDto roleCreateDto){
        RoleCreateDto createdRole = RoleMapper.INSTANCE.mapFromEntity( roleService.create(roleCreateDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRole);
    }

}
