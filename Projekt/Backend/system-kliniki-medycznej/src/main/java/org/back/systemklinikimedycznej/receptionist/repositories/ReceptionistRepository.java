package org.back.systemklinikimedycznej.receptionist.repositories;

import org.back.systemklinikimedycznej.account.repositories.entities.Account;
import org.back.systemklinikimedycznej.receptionist.repositories.entities.Receptionist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReceptionistRepository extends JpaRepository<Receptionist,Long> {
    Optional<Receptionist> findByAccount_Email(String email);

    Optional<Receptionist> findByAccount(Account account);
}
