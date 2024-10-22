package org.back.systemklinikimedycznej.appointment.repositories;

import org.back.systemklinikimedycznej.appointment.repositories.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

    @Query("""
    select ap FROM Appointment as ap
    JOIN ap.doctor as d
    WHERE d.pwzNumber = :pwzNumber AND DATE(ap.appointmentDateTime) BETWEEN :startDate AND :endDate
    """)
    List<Appointment> findAllBetweenDates(LocalDate startDate, LocalDate endDate, String pwzNumber);
}
