package org.back.systemklinikimedycznej.role.controller;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.role.controller.dto.RoleDto;
import org.back.systemklinikimedycznej.role.mapper.RoleMapper;
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
}
