package org.back.systemklinikimedycznej.cure.dto;

public record SearchMedicine(
        String medicinalProductName,
        AdvancedMedicineSearch advancedSearchOptions
) {
}
