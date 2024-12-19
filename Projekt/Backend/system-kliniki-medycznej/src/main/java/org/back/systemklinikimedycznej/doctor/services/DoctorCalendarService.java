package org.back.systemklinikimedycznej.doctor.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.appointment.repositories.entities.Appointment;
import org.back.systemklinikimedycznej.appointment.services.AppointmentService;
import org.back.systemklinikimedycznej.doctor.controller.dto.CalendarAppointment;
import org.back.systemklinikimedycznej.doctor.controller.dto.CalendarAppointments;
import org.back.systemklinikimedycznej.doctor.controller.dto.CalendarAppointmentsResponse;
import org.back.systemklinikimedycznej.doctor.domain.DatePeriod;
import org.back.systemklinikimedycznej.doctor.enums.CalendarFormatType;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.back.systemklinikimedycznej.doctor.util.DoctorCalendarManagerUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DoctorCalendarService {
    private final AppointmentService appointmentService;

    @Transactional
    public CalendarAppointments findCalendarAppointmentsForADoctor(LocalDate startDate, Doctor doctor, CalendarFormatType calendarFormatType) {
        LocalDate endDate = startDate.plusDays(calendarFormatType.getDays());

        List<Appointment> appointments = appointmentService.findAllAppointmentsBetweenDatesForADoctor(startDate, endDate, doctor);

        return DoctorCalendarManagerUtil.mapToCalendarAppointments(appointments);
    }

    @Transactional
    public CalendarAppointmentsResponse findCalendarAppointmentsForADoctorWithDate(LocalDate date, Doctor doctor, CalendarFormatType formatType) {
        DatePeriod datePeriod = DoctorCalendarManagerUtil.getDatePeriod(date, formatType);

        Map<LocalDate, List<Appointment>> appointmentsGroupedByDate = appointmentService.getDoctorAppointmentsGroupedByDate(datePeriod.start(), datePeriod.end(), doctor);
        List<CalendarAppointment> calendarAppointments = createCalendarAppointments(appointmentsGroupedByDate);

        return new CalendarAppointmentsResponse(calendarAppointments, datePeriod.start(), datePeriod.end());
    }

    private List<CalendarAppointment> createCalendarAppointments(Map<LocalDate, List<Appointment>> preparedData) {
        return preparedData.entrySet().stream()
                .map(e -> DoctorCalendarManagerUtil.buildCalendarAppointment(
                        e.getKey(),
                        e.getValue()
                ))
                .toList();
    }

}
