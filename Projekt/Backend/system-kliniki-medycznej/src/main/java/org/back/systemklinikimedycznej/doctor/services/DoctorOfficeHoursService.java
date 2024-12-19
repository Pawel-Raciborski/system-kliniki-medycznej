package org.back.systemklinikimedycznej.doctor.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.appointment.services.AppointmentService;
import org.back.systemklinikimedycznej.doctor.controller.dto.AvailableOfficeHours;
import org.back.systemklinikimedycznej.doctor.controller.dto.OfficeHoursDto;
import org.back.systemklinikimedycznej.doctor.controller.dto.WorkingDays;
import org.back.systemklinikimedycznej.doctor.exceptions.DoctorOfficeHoursException;
import org.back.systemklinikimedycznej.doctor.repositories.DoctorOfficeHoursRepository;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.back.systemklinikimedycznej.doctor.repositories.entities.DoctorOfficeHours;
import org.back.systemklinikimedycznej.doctor.util.OfficeHoursManagerUtil;
import org.back.systemklinikimedycznej.doctor.validators.OfficeHoursValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorOfficeHoursService {
    private final DoctorOfficeHoursRepository doctorOfficeHoursRepository;
    private final OfficeHoursValidator officeHoursValidator;
    private final AppointmentService appointmentService;

    @Transactional
    public DoctorOfficeHours add(Doctor doctorToAddOfficeHours, OfficeHoursDto officeHoursDto) {
        officeHoursValidator.validateDayNotExist(doctorToAddOfficeHours, officeHoursDto);
        DoctorOfficeHours officeHoursToAdd = OfficeHoursManagerUtil.buildDoctorOfficeHours(doctorToAddOfficeHours, officeHoursDto);

        return doctorOfficeHoursRepository.save(officeHoursToAdd);
    }


    @Transactional
    public DoctorOfficeHours update(OfficeHoursDto updatedOfficeHours) {
        DoctorOfficeHours doctorOfficeHoursToUpdate = findById(updatedOfficeHours.id());
        doctorOfficeHoursToUpdate = OfficeHoursManagerUtil.withUpdatedFields(doctorOfficeHoursToUpdate, updatedOfficeHours);

        return doctorOfficeHoursRepository.save(doctorOfficeHoursToUpdate);
    }

    private DoctorOfficeHours findById(Long id) {
        return doctorOfficeHoursRepository.findById(id).orElseThrow(() -> new DoctorOfficeHoursException("Nie znaleziono dnia", HttpStatus.NOT_FOUND));
    }

    @Transactional
    public void delete(Doctor doctorForRemoveOfficeHours, DayOfWeek day) {
        DoctorOfficeHours officeHoursForRemove = findOfficeHoursForDoctorForDay(doctorForRemoveOfficeHours, day);
        doctorOfficeHoursRepository.delete(officeHoursForRemove);

    }

    public DoctorOfficeHours findOfficeHoursForDoctorForDay(Doctor doctor, DayOfWeek day) {
        return doctorOfficeHoursRepository.findByDoctorAndDay(doctor, day)
                .orElseThrow(
                        () -> new DoctorOfficeHoursException("Nie znaleziono dnia [%s] dla podanego doktora".formatted(day), HttpStatus.NOT_FOUND)
                );
    }

    @Transactional
    public AvailableOfficeHours findAvailableHoursInGivenDayForDoctor(Doctor doctor, LocalDate date) {
        DoctorOfficeHours officeHoursForDoctorForDay = findOfficeHoursForDoctorForDay(doctor, date.getDayOfWeek());

        var bookedOfficeHours = appointmentService.findBookedAppointmentHoursForADoctorInGivenDay(doctor, date);

        List<LocalTime> appointmentOfficeHours = OfficeHoursManagerUtil.buildListOfAvailableAppointmentOfficeHours(officeHoursForDoctorForDay, bookedOfficeHours);
        return OfficeHoursManagerUtil.buildAvailableHours(appointmentOfficeHours);
    }

    public List<Integer> findWorkingDays(Doctor doctor) {
        List<DoctorOfficeHours> officeHours = doctorOfficeHoursRepository.findAllByDoctor(doctor);

        return officeHours.stream().map(officeHour -> officeHour.getDay().getValue()).toList();
    }
}
