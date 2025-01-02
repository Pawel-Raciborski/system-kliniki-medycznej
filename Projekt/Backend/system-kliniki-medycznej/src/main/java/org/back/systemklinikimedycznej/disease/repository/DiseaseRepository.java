package org.back.systemklinikimedycznej.disease.repository;

import org.back.systemklinikimedycznej.disease.repository.entities.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiseaseRepository extends JpaRepository<Disease, Long> {
}
