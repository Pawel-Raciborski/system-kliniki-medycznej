package org.back.systemklinikimedycznej.personal_details.repositories;

import org.back.systemklinikimedycznej.personal_details.repositories.entities.PersonalDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonalDetailsRepository extends JpaRepository<PersonalDetails, Long> {
    Optional<PersonalDetails> findByPesel(String pesel);

    Optional<PersonalDetails> findByPhoneNumber(String phoneNumber);
}
