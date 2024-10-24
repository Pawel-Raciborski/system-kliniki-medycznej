package org.back.systemklinikimedycznej.appointment.repositories;

import org.back.systemklinikimedycznej.appointment.repositories.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

    @Query("""
    select ap FROM Appointment as ap
    JOIN ap.doctor as d
    WHERE d.pwzNumber = :pwzNumber AND DATE(ap.appointmentDateTime) BETWEEN :startDate AND :endDate
    """)
    List<Appointment> findAllBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate,@Param("pwzNumber") String pwzNumber);

    @Query(
    """
    SELECT app FROM Appointment as app
    JOIN app.patientCard.patient p
    JOIN p.personalDetails pD
    WHERE pD.pesel = :pesel
    AND app.status = 'CONFIRMED'
    AND DATE(app.appointmentDateTime) >= :currentDate
    ORDER BY app.appointmentDateTime ASC LIMIT 1
    """
    )
    Optional<Appointment> findNextAppointmentForPatient(String pesel, LocalDate currentDate);
}
