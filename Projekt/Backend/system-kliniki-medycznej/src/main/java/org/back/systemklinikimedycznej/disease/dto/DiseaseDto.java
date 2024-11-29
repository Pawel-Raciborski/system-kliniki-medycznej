package org.back.systemklinikimedycznej.disease.dto;

public record DiseaseDto(
        Long id,
        String category,
        String icd11Code,
        String icd11Title,
        String icd10Code,
        String icd10Title
) {
}
