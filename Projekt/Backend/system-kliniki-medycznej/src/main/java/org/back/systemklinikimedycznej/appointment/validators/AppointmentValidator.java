package org.back.systemklinikimedycznej.appointment.validators;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.appointment.exceptions.AppointmentException;
import org.back.systemklinikimedycznej.appointment.repositories.AppointmentRepository;
import org.back.systemklinikimedycznej.appointment.repositories.entities.Appointment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentValidator {
    private final AppointmentRepository appointmentRepository;

    public void validateDateTimeExist(LocalDate date, LocalTime hour) {
        LocalDateTime appointmentDateTime = LocalDateTime.of(date,hour);

        Optional<Appointment> appointmentOptional = appointmentRepository.findByAppointmentDateTime(appointmentDateTime);

        checkIfExistDateAndTime(appointmentOptional.isPresent());
    }

    private void checkIfExistDateAndTime(boolean exist) {
        if(exist){
            throw new AppointmentException("Podana godzina jest już zarezerwowana!", HttpStatus.CONFLICT);
        }
    }
}
