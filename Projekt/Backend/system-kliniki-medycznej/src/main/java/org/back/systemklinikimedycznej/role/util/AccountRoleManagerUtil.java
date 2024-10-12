package org.back.systemklinikimedycznej.role.util;

import org.back.systemklinikimedycznej.account.repositories.entities.Account;
import org.back.systemklinikimedycznej.role.repository.entities.AccountRole;
import org.back.systemklinikimedycznej.role.repository.entities.Role;

public class AccountRoleManagerUtil {
    public static AccountRole buildAccountRole(Account accountToAddRole, Role roleToAdd) {
        return AccountRole.builder()
                .account(accountToAddRole)
                .role(roleToAdd)
                .build();
    }
}
