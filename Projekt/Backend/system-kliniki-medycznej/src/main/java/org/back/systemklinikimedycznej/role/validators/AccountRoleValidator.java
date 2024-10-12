package org.back.systemklinikimedycznej.role.validators;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.role.exceptions.AccountRoleException;
import org.back.systemklinikimedycznej.role.repository.AccountRoleRepository;
import org.back.systemklinikimedycznej.role.repository.entities.AccountRole;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountRoleValidator {
    private final AccountRoleRepository accountRoleRepository;

    public void validateAccountRoleNotExist(AccountRole accountRole){
        Example<AccountRole> accountRoleExample = Example.of(accountRole);

        checkNotExistAccountRole(accountRoleRepository.findOne(accountRoleExample).isPresent());
    }

    private void checkNotExistAccountRole(boolean accountRoleExist) {
        if(accountRoleExist){
            throw new AccountRoleException("Podane konto posiada już taką rolę!", HttpStatus.CONFLICT);
        }
    }
}
