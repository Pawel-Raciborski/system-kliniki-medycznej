package org.back.systemklinikimedycznej.cure.web_client.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SpecimenType {
    HUMAN("L"),
    WET("W");

    private final String name;
}
