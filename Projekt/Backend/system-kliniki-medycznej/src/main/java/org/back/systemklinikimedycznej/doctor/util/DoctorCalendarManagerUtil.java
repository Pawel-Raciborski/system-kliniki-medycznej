package org.back.systemklinikimedycznej.doctor.util;

import org.back.systemklinikimedycznej.appointment.mappers.AppointmentMapper;
import org.back.systemklinikimedycznej.appointment.repositories.entities.Appointment;
import org.back.systemklinikimedycznej.doctor.controller.dto.CalendarAppointments;
import org.back.systemklinikimedycznej.doctor.controller.dto.TodayCalendarAppointments;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class DoctorCalendarManagerUtil {
    public static CalendarAppointments mapToCalendarAppointments(List<Appointment> appointments) {
        var groupedAppointments = appointments.stream().collect(
                Collectors.groupingBy(
                        appointment -> appointment.getAppointmentDateTime().getDayOfWeek(),
                        HashMap::new,
                        Collectors.toList()
                )
        );

        var list = groupedAppointments.entrySet().stream().map(e -> mapToTodayCalendarAppointments(e.getKey(),e.getValue())).toList();

        return new CalendarAppointments(list);
    }

    private static TodayCalendarAppointments mapToTodayCalendarAppointments(DayOfWeek day, List<Appointment> appointments) {
        return TodayCalendarAppointments.builder()
                .day(day.name())
                .appointments(appointments.stream().map(AppointmentMapper.APPOINTMENT_MAPPER::mapToAppointmentInfo).toList())
                .build();
    }
}
