package org.back.systemklinikimedycznej.prescription.repositories;

import org.back.systemklinikimedycznej.prescription.repositories.entities.PrescriptionMedicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionMedicineRepository extends JpaRepository<PrescriptionMedicine,Long> {
}
