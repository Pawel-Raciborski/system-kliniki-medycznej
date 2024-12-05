package org.back.systemklinikimedycznej.account.services;

import org.back.systemklinikimedycznej.account.dto.AccountDto;
import org.back.systemklinikimedycznej.account.exceptions.UsernameAlreadyExistException;
import org.back.systemklinikimedycznej.account.repositories.AccountRepository;
import org.back.systemklinikimedycznej.account.repositories.entities.Account;
import org.back.systemklinikimedycznej.account.validators.AccountValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @InjectMocks
    private AccountService accountService;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private AccountValidator accountValidator;

    @Test
    void shouldCreateAccountCorrectly() {
        //given
        AccountDto accountToCreate = AccountDto.builder().username("janek123").password("janek123").email("janek123@mail.com").build();
        Account createdAccountMock = Account.builder().id(1L).username("janek123").password("janek123").email("janek123@mail.com").build();

        Mockito.doNothing().when(accountValidator).validateEmailAndUsername(accountToCreate.email(),accountToCreate.username());
        Mockito.when(accountRepository.save(Mockito.any(Account.class))).thenReturn(createdAccountMock);

        //when
        Account createdAccount = accountService.create(accountToCreate);

        //then
        Assertions.assertEquals(1L,createdAccount.getId());
        Assertions.assertEquals("janek123",createdAccount.getUsername());

        Mockito.verify(accountValidator).validateEmailAndUsername(accountToCreate.email(),accountToCreate.username());
    }

    @Test
    void shouldThrowAnExceptionDuringUsernameAndEmailValidation(){
        //given
        AccountDto accountToCreate = AccountDto.builder().username("janek123").password("janek123").email("janek123@mail.com").build();
        Account createdAccountMock = Account.builder().id(1L).username("janek123").password("janek123").email("janek123@mail.com").build();

        Mockito.doThrow(new UsernameAlreadyExistException("Nazwa użytkownika już zajęta!", HttpStatus.CONFLICT)).when(accountValidator).validateEmailAndUsername(accountToCreate.email(),accountToCreate.username());

        //then
        UsernameAlreadyExistException usernameAlreadyExistException = Assertions.assertThrows(UsernameAlreadyExistException.class, () -> accountService.create(accountToCreate));
        Assertions.assertEquals("Nazwa użytkownika już zajęta!",usernameAlreadyExistException.getMessage());
    }
}