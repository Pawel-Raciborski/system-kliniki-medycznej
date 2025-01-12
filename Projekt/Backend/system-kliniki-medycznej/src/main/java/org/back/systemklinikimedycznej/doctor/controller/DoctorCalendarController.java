package org.back.systemklinikimedycznej.doctor.controller;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.config.util.DateFormatter;
import org.back.systemklinikimedycznej.doctor.controller.dto.CalendarAppointments;
import org.back.systemklinikimedycznej.doctor.controller.dto.CalendarAppointmentsResponse;
import org.back.systemklinikimedycznej.doctor.enums.CalendarFormatType;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.back.systemklinikimedycznej.doctor.services.DoctorCalendarService;
import org.back.systemklinikimedycznej.doctor.services.DoctorService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Objects;

@RestController
@RequestMapping("/doctors/calendar")
@RequiredArgsConstructor
public class DoctorCalendarController {
    private final DoctorCalendarService doctorCalendarService;
    private final DoctorService doctorService;
    @GetMapping("/appointments")
    public ResponseEntity<CalendarAppointments> getAppointments(
            @RequestParam(name = "date") @DateTimeFormat(pattern = DateFormatter.DATE_FORMAT) LocalDate date,
            @RequestParam(name = "pwzNumber") String doctorPwzNumber,
            @RequestParam(name = "formatType", defaultValue = "week") String calendarFormatType
            ){
        Doctor doctor = doctorService.findByPwzNumber(doctorPwzNumber);
        CalendarAppointments calendarAppointments = doctorCalendarService.findCalendarAppointmentsForADoctor(
                date,doctor, CalendarFormatType.valueOf(calendarFormatType.toUpperCase())
        );

        return ResponseEntity.ok((calendarAppointments));
    }

    @GetMapping("/all-appointments")
    public ResponseEntity<CalendarAppointmentsResponse> getAppointments(
            @RequestParam(name="date", required = false) @DateTimeFormat(pattern = DateFormatter.DATE_FORMAT) LocalDate date,
            @RequestParam(name="doctorId", required = false) Long id,
            @RequestParam(name="formatType", defaultValue = "week") String calendarFormatType
    ){
        CalendarFormatType formatType = CalendarFormatType.valueOf(calendarFormatType.toUpperCase());
        Doctor doctor = doctorService.findById(id);

        CalendarAppointmentsResponse calendarAppointmentsForADoctor = doctorCalendarService.findCalendarAppointmentsForADoctorWithDate(date,doctor, formatType);

        return ResponseEntity.ok(calendarAppointmentsForADoctor);
    }
}
