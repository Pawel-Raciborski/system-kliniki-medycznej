package org.back.systemklinikimedycznej.doctor.controller.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record DoctorsInfo (
        List<DoctorInfo> doctors
){
}
