package org.back.systemklinikimedycznej.personal_details.repositories;

import org.back.systemklinikimedycznej.personal_details.repositories.entities.PersonalDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PersonalDetailsRepository extends JpaRepository<PersonalDetails, Long> {
    Optional<PersonalDetails> findByPesel(String pesel);

    @Query("""
    SELECT pd FROM PersonalDetails as pd
    WHERE pd.phoneNumber IS NOT NULL AND pd.phoneNumber = :phoneNumber
    """)
    Optional<PersonalDetails> findByPhoneNumber(String phoneNumber);
}
