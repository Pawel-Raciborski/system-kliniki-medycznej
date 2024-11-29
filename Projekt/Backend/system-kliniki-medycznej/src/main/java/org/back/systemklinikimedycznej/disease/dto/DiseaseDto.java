package org.back.systemklinikimedycznej.disease.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.back.systemklinikimedycznej.disease.repository.entities.Category;

public record DiseaseDto(
        Long id,
        Category category,
        String icd11Code,
        String icd11Title,
        String icd10Code,
        String icd10Title
) {
}
