package org.back.systemklinikimedycznej.role.controller;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.role.controller.dto.PermissionDto;
import org.back.systemklinikimedycznej.role.controller.dto.RoleDetails;
import org.back.systemklinikimedycznej.role.controller.dto.RolePermissionDto;
import org.back.systemklinikimedycznej.role.mapper.PermissionMapper;
import org.back.systemklinikimedycznej.role.mapper.RolePermissionMapper;
import org.back.systemklinikimedycznej.role.repository.entities.Permission;
import org.back.systemklinikimedycznej.role.repository.entities.RolePermission;
import org.back.systemklinikimedycznej.role.services.RolePermissionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role-permissions")
@RequiredArgsConstructor
public class RolePermissionsController {
    private final RolePermissionService rolePermissionService;
    @PostMapping("/create")
    public ResponseEntity<List<PermissionDto>> create(
            @RequestBody RoleDetails roleDetails
            ){

        List<PermissionDto> permissions = rolePermissionService.create(roleDetails).stream()
                .map(PermissionMapper.INSTANCE::mapFromEntity)
                .toList();
        return ResponseEntity.status(HttpStatus.CREATED).body(permissions);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<PermissionDto> delete(
            @RequestParam("role") String roleName,
            @RequestParam("permission") String permissionName
    ){
       PermissionDto removedPermission = PermissionMapper.INSTANCE.mapFromEntity(rolePermissionService.delete(roleName,permissionName));

        return ResponseEntity.ok(removedPermission);
    }

}
