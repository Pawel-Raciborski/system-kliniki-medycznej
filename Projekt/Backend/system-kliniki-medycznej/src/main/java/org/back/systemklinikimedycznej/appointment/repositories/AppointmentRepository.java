package org.back.systemklinikimedycznej.appointment.repositories;

import org.back.systemklinikimedycznej.appointment.repositories.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
}
