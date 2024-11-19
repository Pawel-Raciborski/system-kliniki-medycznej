package org.back.systemklinikimedycznej.doctor.util;

import org.back.systemklinikimedycznej.appointment.repositories.entities.Appointment;
import org.back.systemklinikimedycznej.appointment.util.AppointmentManagerUtil;
import org.back.systemklinikimedycznej.doctor.controller.dto.*;
import org.back.systemklinikimedycznej.doctor.mapper.OfficeHoursMapper;
import org.back.systemklinikimedycznej.doctor.repositories.entities.DoctorOfficeHours;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
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
                .appointments(appointments.stream().map(AppointmentManagerUtil::buildAppointmentInfo).toList())
                .build();
    }

    public static Map<LocalDate,List<Appointment>> createMap(List<Appointment> appointments) {
        return appointments.stream().collect(
                Collectors.groupingBy(
                        appointment -> appointment.getAppointmentDateTime().toLocalDate(),
                        HashMap::new,
                        Collectors.toList()
                )
        );
    }

    public static CalendarAppointment createCalendarAppointment(LocalDate date, List<Appointment> appointments, DoctorOfficeHours officeHours) {
        List<CalendarAppointmentInfo> calendarAppointmentInfos = appointments.stream().map(DoctorCalendarManagerUtil::createCalendarAppointmentInfo).toList();

        return new CalendarAppointment(date,calendarAppointmentInfos, OfficeHoursMapper.INSTANCE.mapFromEntity(officeHours));
    }

    private static CalendarAppointmentInfo createCalendarAppointmentInfo(Appointment app) {
        return CalendarAppointmentInfo.builder()
                .id(app.getId())
                .hour(app.getAppointmentDateTime().toLocalTime())
                .status(app.getStatus().getAppointmentStatusName())
                .build();
    }
}
