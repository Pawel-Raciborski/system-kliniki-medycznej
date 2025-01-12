package org.back.systemklinikimedycznej.doctor.domain;

public record DoctorSearchParams(
        String doctorFullName,
        AdvancedSearch advancedSearch
) {

}
