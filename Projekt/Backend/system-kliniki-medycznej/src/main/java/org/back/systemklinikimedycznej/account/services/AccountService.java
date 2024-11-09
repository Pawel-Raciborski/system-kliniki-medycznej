package org.back.systemklinikimedycznej.account.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.account.dto.AccountDto;
import org.back.systemklinikimedycznej.account.dto.AccountInfo;
import org.back.systemklinikimedycznej.account.dto.ChangePasswordDto;
import org.back.systemklinikimedycznej.account.exceptions.AccountException;
import org.back.systemklinikimedycznej.account.repositories.AccountRepository;
import org.back.systemklinikimedycznej.account.repositories.entities.Account;
import org.back.systemklinikimedycznej.account.util.AccountManagerUtil;
import org.back.systemklinikimedycznej.account.validators.AccountValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountValidator accountValidator;

    @Transactional
    public Account create(AccountDto accountDto) {
        accountValidator.validateEmailAndUsername(accountDto.email(),accountDto.username());

        Account accountToRegister = AccountManagerUtil.buildUserFromRegistrationForm(accountDto);

        return accountRepository.save(accountToRegister);
    }

    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username)
                .orElseThrow(
                        () -> new AccountException(
                                "Nie znaleziono konta o nazwie użytkownika [%s]!".formatted(username),
                                HttpStatus.NOT_FOUND)
                );
    }

    public Account findByEmail(String email){
        return accountRepository.findByEmail(email)
                .orElseThrow(() -> new AccountException("Nie znaleziono konta z podanym adresem email!",HttpStatus.NOT_FOUND));
    }

    @Transactional
    public void changePassword(ChangePasswordDto changePasswordForm) {
        Account accountToChangePassword = findByEmail(changePasswordForm.userEmail());

        accountValidator.validatePasswordEqual(accountToChangePassword.getPassword(),changePasswordForm.previousPassword());

        AccountManagerUtil.updatePassword(accountToChangePassword,changePasswordForm.newPassword());
        accountRepository.save(accountToChangePassword);
    }

    @Transactional
    public Account updateAccountDetails(Account accountToUpdate, AccountInfo accountInfo) {
        accountValidator.validateEmailAndUsername(accountInfo.newEmail(),accountInfo.newUsername());
        AccountManagerUtil.updateAccountDetails(accountToUpdate, accountInfo.newUsername(), accountInfo.newEmail());

        return accountRepository.save(accountToUpdate);
    }

    public Account findById(Long userId) {
        return accountRepository.findById(userId)
                .orElseThrow(() -> new AccountException("Nie znaleziono konta o podanym identyfikatorze!",HttpStatus.NOT_FOUND));
    }
}
