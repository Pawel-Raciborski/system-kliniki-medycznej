package org.back.systemklinikimedycznej.config.components;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.account.dto.AccountDto;
import org.back.systemklinikimedycznej.account.repositories.entities.Account;
import org.back.systemklinikimedycznej.account.services.AccountService;
import org.back.systemklinikimedycznej.role.repository.AccountRoleRepository;
import org.back.systemklinikimedycznej.role.services.AccountRoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class AdminInit implements CommandLineRunner {
    private final AccountService accountService;
    private final AccountRoleService accountRoleService;
    private final AccountRoleRepository accountRoleRepository;
    @Override
    public void run(String... args) {
        Long numberOfAdminAccounts = accountRoleRepository.countAccountsWithRole("ADMIN");

        if(adminNotExist(numberOfAdminAccounts)){
            AccountDto accountToCreate = createAdminAccount();
            Account createdAdminAccount = accountService.create(accountToCreate);
            addBasicAdminRoles(createdAdminAccount);
        }
    }

    private static boolean adminNotExist(Long adminAccounts) {
        return adminAccounts == 0;
    }

    private void addBasicAdminRoles(Account account) {
        accountRoleService.processAccountRoleCreation(account,"ADMIN");
    }


    private AccountDto createAdminAccount() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Tworzenie admina, wypełnij poniższe dane");
        System.out.print("Nazwa użytkownika[Wymagane!]: ");
        String username = scanner.nextLine();

        System.out.print("Hasło[Wymagane!]: ");
        String password = scanner.nextLine();

        System.out.println("Email: ");
        String email = scanner.nextLine();

        return AccountDto.builder()
                .username(username)
                .password(password)
                .email(email)
                .build();
    }
}
