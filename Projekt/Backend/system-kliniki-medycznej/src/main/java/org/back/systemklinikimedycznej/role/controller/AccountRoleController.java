package org.back.systemklinikimedycznej.role.controller;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.account.repositories.entities.Account;
import org.back.systemklinikimedycznej.account.services.AccountService;
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
    private final AccountService accountService;
    @PostMapping("/create")
    public ResponseEntity<AccountRoleDto> create(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "roleName") String roleName){
        Account accountForAddRole =  accountService.findByUsername(username);
        AccountRoleDto accountRoleDto = AccountRoleMapper.INSTANCE.mapFromEntity(accountRoleService.processAccountRoleCreation(accountForAddRole,roleName));

        return ResponseEntity.status(HttpStatus.CREATED).body(accountRoleDto);
    }

    @GetMapping
    public ResponseEntity<List<RoleDto>> getAccountRoles(@RequestParam(name = "username") String username){
        Account account = accountService.findByUsername(username);
        List<RoleDto> userRoles = accountRoleService.findAllAccountRoles(account).stream().map(RoleMapper.INSTANCE::mapFromEntity).toList();

        return ResponseEntity.ok(userRoles);
    }

    @GetMapping("/available")
    public ResponseEntity<List<String>> getAvailableRolesForUser(@RequestParam(name="username") String username){
        Account account = accountService.findByUsername(username);

        List<String> availableUserRolesToAdd = accountRoleService.findAvailableRolesToAddForAccount(account);
        return ResponseEntity.ok(availableUserRolesToAdd);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<AccountRoleDto> delete(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "roleName") String roleName
    ){
        Account account = accountService.findByUsername(username);
        AccountRoleDto removedAccountRole = AccountRoleMapper.INSTANCE.mapFromEntity(accountRoleService.delete(account,roleName));

        return ResponseEntity.ok(removedAccountRole);
    }
}
