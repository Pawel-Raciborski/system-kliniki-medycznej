package org.back.systemklinikimedycznej.account.repositories;

import org.back.systemklinikimedycznej.account.repositories.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {
    Optional<Account> findByEmail(String email);

    Optional<Account> findByUsername(String username);
}
