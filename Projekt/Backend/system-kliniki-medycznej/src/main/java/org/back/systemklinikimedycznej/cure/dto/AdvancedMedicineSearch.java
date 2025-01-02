package org.back.systemklinikimedycznej.cure.dto;

public record AdvancedMedicineSearch(
        String atcCode,
        String gtinNumber,
        String commonName
) {
}
