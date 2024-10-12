package org.back.systemklinikimedycznej.role.controller;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.role.controller.dto.PermissionDto;
import org.back.systemklinikimedycznej.role.mapper.PermissionMapper;
import org.back.systemklinikimedycznej.role.services.PermissionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
public class PermissionController {
    private final PermissionService permissionService;

    @PostMapping("/create")
    public ResponseEntity<PermissionDto> create(@RequestBody PermissionDto permission){
        PermissionDto createdPermission = PermissionMapper.INSTANCE.mapFromEntity(permissionService.create(permission));

        return ResponseEntity.status(HttpStatus.CREATED).body(createdPermission);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<PermissionDto> delete(@RequestParam(name = "name")String permissionName){
        PermissionDto removedPermission = PermissionMapper.INSTANCE.mapFromEntity(permissionService.delete(permissionName));

        return ResponseEntity.ok(removedPermission);
    }
}
