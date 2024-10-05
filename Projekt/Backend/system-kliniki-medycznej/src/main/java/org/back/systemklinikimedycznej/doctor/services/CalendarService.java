package org.back.systemklinikimedycznej.doctor.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.doctor.repositories.CalendarRepository;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.back.systemklinikimedycznej.doctor.repositories.entities.calendar.DoctorCalendar;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final CalendarRepository calendarRepository;

    public DoctorCalendar createCalendarForDoctor(Doctor createdDoctor){
        DoctorCalendar doctorCalendar = buildDoctorCalendar(createdDoctor);
        return calendarRepository.save(doctorCalendar);
    }

    private DoctorCalendar buildDoctorCalendar(Doctor createdDoctor) {
        return DoctorCalendar.builder()
                .doctor(createdDoctor)
                .createdAt(createdDoctor.getAccount().getDateTimeOfCreation())
                .lastModified(createdDoctor.getAccount().getDateTimeOfCreation())
                .build();
    }
}
