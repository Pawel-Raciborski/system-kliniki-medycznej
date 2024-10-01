package org.back.systemklinikimedycznej.patient.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum BloodType {
    A_PLUS ("A+"),
    A_MINUS ("A-"),
    B_PLUS ("B+"),
    B_MINUS ("B-"),
    AB_PLUS ("AB+"),
    AB_MINUS ("AB-"),
    ZERO_PLUS ("O+"),
    ZERO_MINUS("O-");

    private final String bloodTypeName;
}
