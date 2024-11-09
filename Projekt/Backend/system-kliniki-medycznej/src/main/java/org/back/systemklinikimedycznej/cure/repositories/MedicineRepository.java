package org.back.systemklinikimedycznej.cure.repositories;

import org.back.systemklinikimedycznej.cure.repositories.entities.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicineRepository extends JpaRepository<Medicine,Integer> {
    Optional<Medicine> findByRegistryNumber(String registryNumber);
}
