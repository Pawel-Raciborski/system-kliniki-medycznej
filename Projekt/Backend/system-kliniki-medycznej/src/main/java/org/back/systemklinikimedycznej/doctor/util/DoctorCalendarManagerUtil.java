package org.back.systemklinikimedycznej.doctor.util;

import org.back.systemklinikimedycznej.appointment.repositories.entities.Appointment;
import org.back.systemklinikimedycznej.appointment.util.AppointmentManagerUtil;
import org.back.systemklinikimedycznej.doctor.controller.dto.*;
import org.back.systemklinikimedycznej.doctor.domain.DatePeriod;
import org.back.systemklinikimedycznej.doctor.enums.CalendarFormatType;
import org.back.systemklinikimedycznej.doctor.mapper.OfficeHoursMapper;
import org.back.systemklinikimedycznej.doctor.repositories.entities.DoctorOfficeHours;
import org.back.systemklinikimedycznej.personal_details.repositories.entities.PersonalDetails;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
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

    public static CalendarAppointment buildCalendarAppointment(LocalDate date, List<Appointment> appointments) {
        List<CalendarAppointmentInfo> calendarAppointmentInfos = appointments.stream().map(DoctorCalendarManagerUtil::buildCalendarAppointmentInfo).toList();

        return new CalendarAppointment(date,calendarAppointmentInfos);
    }

    private static CalendarAppointmentInfo buildCalendarAppointmentInfo(Appointment app) {
        PersonalDetails personalDetails = app.getDoctor().getPersonalDetails();
        return CalendarAppointmentInfo.builder()
                .id(app.getId())
                .hour(app.getAppointmentDateTime().toLocalTime())
                .status(app.getStatus().getAppointmentStatusName())
                .patientFullName(personalDetails.getName()+ " " + personalDetails.getSurname())
                .build();
    }

    /**
     * Method responsible for return week date period
     * @param date date of the week
     * @param formatType responsible for choose how long period is
     *
     * @return Returns DatePeriod with beginning of the week/month and end of the week/month depending on selected formatType
     * */
    public static DatePeriod getDatePeriod(LocalDate date, CalendarFormatType formatType) {
        LocalDate now = LocalDate.now();

        if (!Objects.isNull(date)) {
            now = date;
        }

        LocalDate start = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate end = now.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));

        if (CalendarFormatType.MONTH == formatType) {
            start = now.with(TemporalAdjusters.firstDayOfMonth());
            end = now.with(TemporalAdjusters.lastDayOfMonth());
        }
        return new DatePeriod(start, end);
    }
}
