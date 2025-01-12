package org.back.systemklinikimedycznej.doctor.repositories;

import org.back.systemklinikimedycznej.account.repositories.entities.Account;
import org.back.systemklinikimedycznej.doctor.controller.dto.DoctorInfo;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByPwzNumber(String pwzNumber);

    Optional<Doctor> findByAccount(Account account);

    @Query("""
            SELECT d FROM Doctor d
            JOIN d.personalDetails as pd
            WHERE pd.name LIKE %:name AND pd.surname LIKE :surname%
            """)
    List<DoctorInfo> findByNameAndSurname(@Param("name") String name, @Param("surname") String surname);

    @Query("""
            SELECT d FROM Doctor as d
            JOIN d.personalDetails as pd
            JOIN d.doctorSpecializations ds
            WHERE pd.name LIKE :name% AND pd.surname LIKE :surname%
            AND ds.name IN :specializations
            """)
    List<Doctor> findByNameAndSurnameWithSpecializations(@Param("name") String name, @Param("surname") String surname, @Param("specializations") List<String> specializations);

    @Query(
    """
    SELECT d FROM Doctor d
    JOIN d.personalDetails as pd
    """)
    List<Doctor> findAllByNameAndSurnameWithPwzNumber(String name, String surname, String pwzNumber);
}
