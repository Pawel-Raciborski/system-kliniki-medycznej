package org.back.systemklinikimedycznej.doctor.domain;

import lombok.Builder;

import java.util.List;

@Builder
public record AdvancedSearch(
        String pwzNumber,
        List<String> selectedSpecializations
) {
}
