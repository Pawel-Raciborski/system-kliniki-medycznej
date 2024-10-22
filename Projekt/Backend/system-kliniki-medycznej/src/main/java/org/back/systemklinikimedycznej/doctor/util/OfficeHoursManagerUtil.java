package org.back.systemklinikimedycznej.doctor.util;

import lombok.experimental.UtilityClass;
import org.back.systemklinikimedycznej.doctor.controller.dto.OfficeHoursDto;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.back.systemklinikimedycznej.doctor.repositories.entities.DoctorOfficeHours;

import java.time.LocalDate;

@UtilityClass
public class OfficeHoursManagerUtil {

    public static DoctorOfficeHours withUpdatedFields(DoctorOfficeHours doctorOfficeHours, OfficeHoursDto updatedOfficeHours) {
        return doctorOfficeHours.withStartHour(updatedOfficeHours.startHour())
                .withEndHour(updatedOfficeHours.endHour())
                .withDay(updatedOfficeHours.day())
                .withDurationInMinutes(updatedOfficeHours.durationInMinutes())
                .withModifiedDate(LocalDate.now());
    }

    public static DoctorOfficeHours buildDoctorOfficeHours(Doctor doctorToAddOfficeHours, OfficeHoursDto officeHoursDto) {
        return DoctorOfficeHours.builder()
                .endHour(officeHoursDto.endHour())
                .startHour(officeHoursDto.startHour())
                .day(officeHoursDto.day())
                .doctor(doctorToAddOfficeHours)
                .modifiedDate(LocalDate.now())
                .durationInMinutes(officeHoursDto.durationInMinutes())
                .build();
    }
}
