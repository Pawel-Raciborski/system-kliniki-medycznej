package org.back.systemklinikimedycznej.doctor.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.appointment.repositories.entities.Appointment;
import org.back.systemklinikimedycznej.appointment.services.AppointmentService;
import org.back.systemklinikimedycznej.doctor.controller.dto.CalendarAppointment;
import org.back.systemklinikimedycznej.doctor.controller.dto.CalendarAppointments;
import org.back.systemklinikimedycznej.doctor.controller.dto.CalendarAppointmentsResponse;
import org.back.systemklinikimedycznej.doctor.enums.CalendarFormatType;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.back.systemklinikimedycznej.doctor.util.DoctorCalendarManagerUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DoctorCalendarService {
    private final AppointmentService appointmentService;
    private final DoctorOfficeHoursService doctorOfficeHoursService;

    @Transactional
    public CalendarAppointments findCalendarAppointmentsForADoctor(LocalDate startDate, Doctor doctor, CalendarFormatType calendarFormatType) {
        LocalDate endDate = startDate.plusDays(calendarFormatType.getDays());

        List<Appointment> appointments = appointmentService.findAllAppointmentsBetweenDatesForADoctor(startDate, endDate, doctor);

        return DoctorCalendarManagerUtil.mapToCalendarAppointments(appointments);
    }

    @Transactional
    public CalendarAppointmentsResponse findCalendarAppointmentsForADoctorWithDate(LocalDate date, Doctor doctor, CalendarFormatType formatType) {
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

        Map<LocalDate, List<Appointment>> appointmentsGroupedByDate = appointmentService.getAppointmentsGroupedByDate(start, end, doctor);
        List<CalendarAppointment> calendarAppointments = createCalendarAppointments(doctor, appointmentsGroupedByDate);

        return new CalendarAppointmentsResponse(calendarAppointments, start, end);
    }

    private List<CalendarAppointment> createCalendarAppointments(Doctor doctor, Map<LocalDate, List<Appointment>> preparedData) {
        return preparedData.entrySet().stream()
                .map(e -> DoctorCalendarManagerUtil.buildCalendarAppointment(
                        e.getKey(),
                        e.getValue()
                ))
                .toList();
    }
}
