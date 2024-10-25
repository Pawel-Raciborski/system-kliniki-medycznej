package org.back.systemklinikimedycznej.doctor.util;

import lombok.experimental.UtilityClass;
import org.back.systemklinikimedycznej.doctor.controller.dto.AvailableOfficeHours;
import org.back.systemklinikimedycznej.doctor.controller.dto.OfficeHoursDto;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.back.systemklinikimedycznej.doctor.repositories.entities.DoctorOfficeHours;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Stream;

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

    public static List<LocalTime> buildListOfAvailableAppointmentOfficeHours(DoctorOfficeHours officeHoursForDoctorForDay, List<LocalTime> bookedOfficeHours) {
        return Stream.iterate(
                officeHoursForDoctorForDay.getStartHour(),
                localTime -> !localTime.isAfter(officeHoursForDoctorForDay.getEndHour()),
                localTime -> localTime.plusMinutes(officeHoursForDoctorForDay.getDurationInMinutes())
        ).filter(localTime -> !bookedOfficeHours.contains(localTime)).toList();
    }

    public static AvailableOfficeHours buildAvailableHours(List<LocalTime> appointmentOfficeHours) {
        return AvailableOfficeHours.builder()
                .officeHours(appointmentOfficeHours)
                .build();
    }
}
