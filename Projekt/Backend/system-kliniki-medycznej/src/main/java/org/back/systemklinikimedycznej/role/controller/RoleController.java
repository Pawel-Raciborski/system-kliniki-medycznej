package org.back.systemklinikimedycznej.role.controller;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.role.controller.dto.PermissionDto;
import org.back.systemklinikimedycznej.role.controller.dto.RoleDetails;
import org.back.systemklinikimedycznej.role.controller.dto.RoleDto;
import org.back.systemklinikimedycznej.role.mapper.PermissionMapper;
import org.back.systemklinikimedycznej.role.mapper.RoleMapper;
import org.back.systemklinikimedycznej.role.repository.entities.Role;
import org.back.systemklinikimedycznej.role.services.PermissionService;
import org.back.systemklinikimedycznej.role.services.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;
    private final PermissionService permissionService;
    @PostMapping("/create")
    public ResponseEntity<RoleDto> create(@RequestBody RoleDto role){
        RoleDto createdRole = RoleMapper.INSTANCE.mapFromEntity( roleService.create(role));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRole);
    }

    @PutMapping("/update")
    public ResponseEntity<RoleDto> update(
            @RequestParam(name="previousName") String previousName,
            @RequestBody RoleDto role
    ){

        RoleDto updatedRole = RoleMapper.INSTANCE.mapFromEntity(roleService.update(previousName,role));

        return ResponseEntity.ok(updatedRole);
    }

    @GetMapping
    public ResponseEntity<List<RoleDto>> allRoles(){
        List<RoleDto> roles = roleService.getAllRoles().stream().map(RoleMapper.INSTANCE::mapFromEntity).toList();
        return ResponseEntity.ok(roles);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<RoleDto> delete(
            @RequestParam(name = "name") String roleName
    ){
        RoleDto removedRole = RoleMapper.INSTANCE.mapFromEntity(roleService.delete(roleName));

        return ResponseEntity.ok(removedRole);
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<RoleDetails> getRoleDetails(
            @PathVariable("id") Long roleId
    ){
        Role role = roleService.findById(roleId);
        RoleDetails roleDetails = roleService.getRoleDetails(role);

        return ResponseEntity.ok(roleDetails);
    }

    @GetMapping("/{id}/available-permissions")
    public ResponseEntity<List<PermissionDto>> findAvailablePermissionsForRole(
            @PathVariable("id") Long roleId
    ){
        Role role = roleService.findById(roleId);
        var permissions = permissionService.findAvailablePermissionsForRole(role).stream()
                .map(PermissionMapper.INSTANCE::mapFromEntity)
                .toList();

        return ResponseEntity.ok(permissions);
    }
}
