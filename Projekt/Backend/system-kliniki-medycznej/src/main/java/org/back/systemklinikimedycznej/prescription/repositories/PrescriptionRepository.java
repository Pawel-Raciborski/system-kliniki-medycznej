package org.back.systemklinikimedycznej.prescription.repositories;

import org.back.systemklinikimedycznej.prescription.repositories.entities.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository extends JpaRepository<Prescription,Long> {
}
