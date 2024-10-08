package org.back.systemklinikimedycznej.cure.dto;

import java.util.List;

public record MedicineListDto(
        List<MedicineDto> content
) {
}
