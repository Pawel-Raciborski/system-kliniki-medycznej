package org.back.systemklinikimedycznej.patient.domain;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public enum CureStatus {
    CURING("W trakcie");

    private final String value;

    public static List<CureStatus> findAllByStatus(String cureStatus) {
        if(cureStatus.equals("ALL")){
            return Arrays.asList(CureStatus.values());
        }

        return List.of(CureStatus.valueOf(cureStatus.toUpperCase()));
    }
}
