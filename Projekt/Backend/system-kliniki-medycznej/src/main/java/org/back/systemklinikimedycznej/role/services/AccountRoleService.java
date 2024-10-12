package org.back.systemklinikimedycznej.role.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.account.repositories.entities.Account;
import org.back.systemklinikimedycznej.account.services.AccountService;
import org.back.systemklinikimedycznej.role.exceptions.AccountRoleException;
import org.back.systemklinikimedycznej.role.repository.AccountRoleRepository;
import org.back.systemklinikimedycznej.role.repository.entities.AccountRole;
import org.back.systemklinikimedycznej.role.repository.entities.Role;
import org.back.systemklinikimedycznej.role.util.AccountRoleManagerUtil;
import org.back.systemklinikimedycznej.role.validators.AccountRoleValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountRoleService {
    private final AccountService accountService;
    private final RoleService roleService;
    private final AccountRoleValidator accountRoleValidator;
    private final AccountRoleRepository accountRoleRepository;
    @Transactional
    public AccountRole processAccountRoleCreation(String username, String roleName) {
        Account accountToAddRole = accountService.findByUsername(username);
        Role roleToAdd = roleService.findByName(roleName);

        AccountRole accountRoleToCreate = AccountRoleManagerUtil.buildAccountRole(accountToAddRole, roleToAdd);

        accountRoleValidator.validateAccountRoleNotExist(accountRoleToCreate);

        return accountRoleRepository.save(accountRoleToCreate);
    }


    public List<Role> findAllAccountRoles(String username) {
        return accountRoleRepository.findAllAccountRoles(username);
    }

    @Transactional
    public AccountRole delete(String username, String roleName) {
        Account account= accountService.findByUsername(username);
        Role role = roleService.findByName(roleName);
        AccountRole accountRoleToRemove = findByAccountAndRole(account,role);

        accountRoleRepository.delete(accountRoleToRemove);
        return accountRoleToRemove;
    }

    private AccountRole findByAccountAndRole(Account account, Role role) {
        return accountRoleRepository.findByAccountAndRole(account,role)
                .orElseThrow(() -> new AccountRoleException("Nie znaleziono roli przypisanej do tego konta", HttpStatus.NOT_FOUND));
    }

}
