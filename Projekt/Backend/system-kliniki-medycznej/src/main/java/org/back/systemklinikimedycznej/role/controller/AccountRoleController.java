package org.back.systemklinikimedycznej.role.controller;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.role.controller.dto.AccountRoleDto;
import org.back.systemklinikimedycznej.role.controller.dto.RoleDto;
import org.back.systemklinikimedycznej.role.mapper.AccountRoleMapper;
import org.back.systemklinikimedycznej.role.mapper.RoleMapper;
import org.back.systemklinikimedycznej.role.services.AccountRoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account-roles")
@RequiredArgsConstructor
public class AccountRoleController {
    private final AccountRoleService accountRoleService;
    @PostMapping("/create")
    public ResponseEntity<AccountRoleDto> create(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "roleName") String roleName){
        AccountRoleDto accountRoleDto = AccountRoleMapper.INSTANCE.mapFromEntity(accountRoleService.processAccountRoleCreation(username,roleName));

        return ResponseEntity.status(HttpStatus.CREATED).body(accountRoleDto);
    }

    @GetMapping
    public ResponseEntity<List<RoleDto>> getAccountRoles(@RequestParam(name = "username") String username){
        List<RoleDto> allUserRoles = accountRoleService.findAllAccountRoles(username).stream().map(RoleMapper.INSTANCE::mapFromEntity).toList();

        return ResponseEntity.ok(allUserRoles);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<AccountRoleDto> delete(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "roleName") String roleName
    ){
        AccountRoleDto removedAccountRole = AccountRoleMapper.INSTANCE.mapFromEntity(accountRoleService.delete(username,roleName));

        return ResponseEntity.ok(removedAccountRole);
    }
}
