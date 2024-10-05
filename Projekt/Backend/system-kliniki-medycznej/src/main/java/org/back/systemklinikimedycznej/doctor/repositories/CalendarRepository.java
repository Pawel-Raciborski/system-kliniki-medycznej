package org.back.systemklinikimedycznej.doctor.repositories;

import org.back.systemklinikimedycznej.doctor.repositories.entities.calendar.DoctorCalendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarRepository extends JpaRepository<DoctorCalendar,Long> {
}
