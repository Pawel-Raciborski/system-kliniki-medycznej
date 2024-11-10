package org.back.systemklinikimedycznej.prescription.repositories;

import org.back.systemklinikimedycznej.prescription.repositories.entities.Prescription;
import org.back.systemklinikimedycznej.prescription.repositories.entities.PrescriptionMedicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrescriptionMedicineRepository extends JpaRepository<PrescriptionMedicine,Long> {
    List<PrescriptionMedicine> findAllByPrescription(Prescription prescription);
}
