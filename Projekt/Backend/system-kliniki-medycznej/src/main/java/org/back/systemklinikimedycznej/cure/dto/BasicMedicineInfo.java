package org.back.systemklinikimedycznej.cure.dto;

import java.time.LocalDate;

public record BasicMedicineInfo(
        String registryNumber,
        String dosage
) {
}
