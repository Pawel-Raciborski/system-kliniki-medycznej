package org.back.systemklinikimedycznej.cure.dto;

public record MedicineDto(
        Long id,
        String specimenType,
        String medicinalProductName,
        String commonName,
        String pharmaceuticalFormName,
        String medicinalProductPower,
        String activeSubstanceName,
        String subjectMedicinalProductName,
        String registryNumber,
        String procedureTypeName,
        String expirationDateString,
        String atcCode,
        String targetSpecies
) {
}
