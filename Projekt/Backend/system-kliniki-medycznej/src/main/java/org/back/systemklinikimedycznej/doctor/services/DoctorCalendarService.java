package org.back.systemklinikimedycznej.doctor.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.appointment.repositories.entities.Appointment;
import org.back.systemklinikimedycznej.appointment.services.AppointmentService;
import org.back.systemklinikimedycznej.doctor.controller.dto.CalendarAppointments;
import org.back.systemklinikimedycznej.doctor.enums.CalendarFormatType;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.back.systemklinikimedycznej.doctor.util.DoctorCalendarManagerUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorCalendarService {
    private final AppointmentService appointmentService;

    @Transactional
    public CalendarAppointments findCalendarAppointmentsForADoctor(LocalDate startDate, Doctor doctor, CalendarFormatType calendarFormatType) {
        LocalDate endDate = startDate.plusDays(calendarFormatType.getDays());

        List<Appointment> appointments = appointmentService.findAllAppointmentsBetweenDatesForADoctor(startDate,endDate,doctor);

        return DoctorCalendarManagerUtil.mapToCalendarAppointments(appointments);
    }
}
