package org.back.systemklinikimedycznej.doctor.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.doctor.controller.dto.OfficeHoursDto;
import org.back.systemklinikimedycznej.doctor.exceptions.DoctorOfficeHoursException;
import org.back.systemklinikimedycznej.doctor.repositories.DoctorOfficeHoursRepository;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.back.systemklinikimedycznej.doctor.repositories.entities.DoctorOfficeHours;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorOfficeHoursService {
    private final DoctorOfficeHoursRepository doctorOfficeHoursRepository;

    @Transactional
    public DoctorOfficeHours add(Doctor doctorToAddOfficeHours, OfficeHoursDto officeHoursDto) {
        Optional<DoctorOfficeHours> existOfficeHoursOpt = doctorOfficeHoursRepository.findByDoctorAndDay(doctorToAddOfficeHours, officeHoursDto.day());

        if(existOfficeHoursOpt.isPresent()){
            throw new DoctorOfficeHoursException("Podany dzień jest już zarezerwowany dla podanego lekarza!", HttpStatus.CONFLICT);
        }
        DoctorOfficeHours officeHoursToAdd = buildDoctorOfficeHours(doctorToAddOfficeHours, officeHoursDto);

        return doctorOfficeHoursRepository.save(officeHoursToAdd);
    }

    @Transactional
    public DoctorOfficeHours update(Doctor doctorToUpdateOfficeHours, OfficeHoursDto updatedOfficeHours) {
        Optional<DoctorOfficeHours> doctorOfficeHoursOpt = doctorOfficeHoursRepository.findByDoctorAndDay(doctorToUpdateOfficeHours,updatedOfficeHours.day());

        if(doctorOfficeHoursOpt.isEmpty()){
            throw new DoctorOfficeHoursException("Nie znaleziono godziń pracy w dniu [%s] dla doktora".formatted(updatedOfficeHours.day()),HttpStatus.NOT_FOUND);
        }

        DoctorOfficeHours doctorOfficeHoursToUpdate = withUpdatedFields(doctorOfficeHoursOpt.get(),updatedOfficeHours);

        return doctorOfficeHoursRepository.save(doctorOfficeHoursToUpdate);
    }

    @Transactional
    public void delete(Doctor doctorForRemoveOfficeHours, String day) {
        DayOfWeek dayToRemove = DayOfWeek.valueOf(day.toUpperCase());

        Optional<DoctorOfficeHours> officeHoursForRemove = doctorOfficeHoursRepository.findByDoctorAndDay(doctorForRemoveOfficeHours, dayToRemove);

        if(officeHoursForRemove.isEmpty()){
            throw new DoctorOfficeHoursException("Nie znaleziono dnia [%s] dla doktora".formatted(day),HttpStatus.NOT_FOUND);
        }

        doctorOfficeHoursRepository.delete(officeHoursForRemove.get());

    }

    public DoctorOfficeHours findOfficeHoursForDoctorForDay(Doctor doctor, String day) {
        return doctorOfficeHoursRepository.findByDoctorAndDay(doctor,DayOfWeek.valueOf(day.toUpperCase()))
                .orElseThrow(
                        () -> new DoctorOfficeHoursException("Nie znaleziono godzin pracy dla dnia [%s]".formatted(day),HttpStatus.NOT_FOUND)
                );
    }

    private DoctorOfficeHours withUpdatedFields(DoctorOfficeHours doctorOfficeHours, OfficeHoursDto updatedOfficeHours) {
        return doctorOfficeHours.withStartHour(updatedOfficeHours.startTime())
                .withEndHour(updatedOfficeHours.endTime())
                .withDay(updatedOfficeHours.day())
                .withModifiedDate(LocalDate.now());
    }

    private DoctorOfficeHours buildDoctorOfficeHours(Doctor doctorToAddOfficeHours, OfficeHoursDto officeHoursDto) {
        return DoctorOfficeHours.builder()
                .endHour(officeHoursDto.endTime())
                .startHour(officeHoursDto.startTime())
                .day(officeHoursDto.day())
                .doctor(doctorToAddOfficeHours)
                .modifiedDate(LocalDate.now())
                .build();
    }
}
