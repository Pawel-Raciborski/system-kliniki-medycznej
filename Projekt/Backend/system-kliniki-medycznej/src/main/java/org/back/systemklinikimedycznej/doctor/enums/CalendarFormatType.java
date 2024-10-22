package org.back.systemklinikimedycznej.doctor.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CalendarFormatType {
    TODAY(0),
    WEEK(7),
    MONTH(31);

    private final Integer days;
}
