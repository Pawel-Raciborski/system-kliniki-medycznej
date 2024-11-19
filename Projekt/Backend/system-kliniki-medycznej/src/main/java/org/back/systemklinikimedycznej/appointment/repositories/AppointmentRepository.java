package org.back.systemklinikimedycznej.appointment.repositories;

import org.back.systemklinikimedycznej.appointment.repositories.entities.Appointment;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_card.PatientCard;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

    @Query("""
    select ap FROM Appointment as ap
    JOIN ap.doctor as d
    WHERE d.pwzNumber = :pwzNumber
    AND ap.status IN (org.back.systemklinikimedycznej.appointment.domain.AppointmentStatus.SCHEDULED,org.back.systemklinikimedycznej.appointment.domain.AppointmentStatus.CONFIRMED)
    AND DATE(ap.appointmentDateTime) BETWEEN :startDate AND :endDate
    """)
    List<Appointment> findAllBetweenDatesForADoctor(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("pwzNumber") String pwzNumber);

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

    @Query(value = """
    SELECT DISTINCT app.appointmentDateTime FROM Appointment as app
    JOIN app.doctor d
    WHERE d.pwzNumber = :pwzNumber
    AND DATE(app.appointmentDateTime) = :date
    AND app.status NOT IN (org.back.systemklinikimedycznej.appointment.domain.AppointmentStatus.CANCELLED)
    """)
    List<LocalDateTime> findBookedAppointmentHoursForADoctorInGivenDay(@Param("pwzNumber") String pwzNumber,@Param("date") LocalDate date);

    @Query("""
    SELECT app FROM Appointment as app
    JOIN app.patientCard as pc
    WHERE pc.patient.personalDetails.pesel = :pesel
    AND app.status NOT IN (
    org.back.systemklinikimedycznej.appointment.domain.AppointmentStatus.CANCELLED,
    org.back.systemklinikimedycznej.appointment.domain.AppointmentStatus.CHECK_OUT,
    org.back.systemklinikimedycznej.appointment.domain.AppointmentStatus.IN_PROGRESS
    )
    """)
    List<Appointment> findUpcomingAppointments(Pageable pageable, @Param("pesel") String pesel);

    @Query("""
    SELECT COUNT(app) FROM Appointment app
    WHERE app.patientCard = :patientCard
    AND app.status = org.back.systemklinikimedycznej.appointment.domain.AppointmentStatus.SCHEDULED
    """)
    Long countPatientUpcomingAppointments(@Param("patientCard") PatientCard patientCard);

    @Query("""
    SELECT app FROM Appointment app
    WHERE app.patientCard = :patientCard
    AND app.status = org.back.systemklinikimedycznej.appointment.domain.AppointmentStatus.SCHEDULED
    ORDER BY app.appointmentDateTime DESC
    LIMIT 1
    """)
    Optional<Appointment> findNextPatientAppointment(@Param("patientCard") PatientCard patientCard);

    Optional<Appointment> findByAppointmentDateTime(LocalDateTime dateTime);
}
