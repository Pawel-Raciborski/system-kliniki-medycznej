package org.back.systemklinikimedycznej.account.controller;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.account.dto.AccountInfo;
import org.back.systemklinikimedycznej.account.dto.ChangePasswordDto;
import org.back.systemklinikimedycznej.account.mapper.AccountMapper;
import org.back.systemklinikimedycznej.account.repositories.entities.Account;
import org.back.systemklinikimedycznej.account.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    @PutMapping("/change-password")
    public ResponseEntity<Void> changePassword(
        @RequestBody ChangePasswordDto changePasswordForm
    ){
        accountService.changePassword(changePasswordForm);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/update-account-details")
    public ResponseEntity<AccountInfo> updateAccountDetails(
            @RequestParam(name = "username") String username,
            @RequestBody AccountInfo newAccountData
    ){
        Account accountToUpdate = accountService.findByUsername(username);
        AccountInfo updatedAccountData = AccountMapper.INSTANCE.mapFromAccountToAccountInfo(accountService.updateAccountDetails(accountToUpdate,newAccountData));

        return ResponseEntity.ok(updatedAccountData);
    }
}
