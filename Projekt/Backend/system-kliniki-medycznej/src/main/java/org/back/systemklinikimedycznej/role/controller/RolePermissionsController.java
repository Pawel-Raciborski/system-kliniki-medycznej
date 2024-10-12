package org.back.systemklinikimedycznej.role.controller;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.role.controller.dto.RolePermissionDto;
import org.back.systemklinikimedycznej.role.mapper.RolePermissionMapper;
import org.back.systemklinikimedycznej.role.repository.entities.RolePermission;
import org.back.systemklinikimedycznej.role.services.RolePermissionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role-permissions")
@RequiredArgsConstructor
public class RolePermissionsController {
    private final RolePermissionService rolePermissionService;
    @PostMapping("/create")
    public ResponseEntity<RolePermissionDto> create(
            @RequestParam("role") String roleName,
            @RequestParam("permission") String permissionName
    ){
         RolePermissionDto createdRolePermission = RolePermissionMapper.INSTANCE.mapFromEntity(
                 rolePermissionService.create(roleName,permissionName)
         );

         return ResponseEntity.status(HttpStatus.CREATED).body(createdRolePermission);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<RolePermissionDto> delete(
            @RequestParam("role") String roleName,
            @RequestParam("permission") String permissionName
    ){
        RolePermissionDto removedRolePermission = RolePermissionMapper.INSTANCE.mapFromEntity(rolePermissionService.delete(roleName,permissionName));
        
        return ResponseEntity.ok(removedRolePermission);
    }

}
