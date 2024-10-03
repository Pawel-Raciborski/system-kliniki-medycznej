package org.back.systemklinikimedycznej.doctor.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.doctor.controller.dto.DoctorSpecializationDto;
import org.back.systemklinikimedycznej.doctor.exceptions.DoctorSpecializationExistException;
import org.back.systemklinikimedycznej.doctor.exceptions.DoctorSpecializationNotFoundException;
import org.back.systemklinikimedycznej.doctor.mapper.DoctorSpecializationMapper;
import org.back.systemklinikimedycznej.doctor.repositories.DoctorSpecializationRepository;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.back.systemklinikimedycznej.doctor.repositories.entities.DoctorSpecialization;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorSpecializationsService {
    private final DoctorSpecializationRepository doctorSpecializationRepository;

    @Transactional
    public List<DoctorSpecialization> addSpecializationToDoctor(
            Doctor doctor,
            List<DoctorSpecializationDto> doctorSpecializationDtos) {

        checkSpecializationsExistForDoctor(doctor,doctorSpecializationDtos);

        var doctorSpecializations = createDoctorSpecializationsSet(doctorSpecializationDtos);
        doctorSpecializations.forEach(doctorSpecialization -> doctorSpecialization.setDoctor(doctor));

        return doctorSpecializationRepository.saveAll(doctorSpecializations);
    }

    private Set<DoctorSpecialization> createDoctorSpecializationsSet(List<DoctorSpecializationDto> doctorSpecializationDtos) {
        return doctorSpecializationDtos.stream()
                .map(DoctorSpecializationMapper.INSTANCE::mapFromDto)
                .collect(Collectors.toSet());
    }

    private void checkSpecializationsExistForDoctor(Doctor doctor, List<DoctorSpecializationDto> doctorSpecializations) {
        for(DoctorSpecializationDto doctorSpecializationDto: doctorSpecializations){
            if(doctorSpecializationRepository.findByName(doctorSpecializationDto.name()).isPresent()){
                throw new DoctorSpecializationExistException("Lekarz posiada już specjalizację o nazwie [%s]!".formatted(doctorSpecializationDto.name()),HttpStatus.CONFLICT);
            }

            Example<DoctorSpecialization> exampleToFind = Example.of(buildDoctorSpecialization(doctor,doctorSpecializationDto));

            if(doctorSpecializationRepository.exists(exampleToFind)){
                throw new DoctorSpecializationExistException("Podana specjalizacja została dodana do lekarza", HttpStatus.CONFLICT);
            }
        }
    }

    private DoctorSpecialization buildDoctorSpecialization(Doctor doctor, DoctorSpecializationDto doctorSpecializationDto) {
        return DoctorSpecialization.builder()
                .doctor(doctor)
                .name(doctorSpecializationDto.name())
                .description(doctorSpecializationDto.description())
                .realizedDate(doctorSpecializationDto.realizedDate())
                .build();
    }

    public List<DoctorSpecialization> findAllSpecializationsForDoctor(Doctor doctor) {
        return doctorSpecializationRepository.findAllByDoctor(doctor);
    }

    @Transactional
    public DoctorSpecialization removeDoctorSpecialization(Doctor doctor, String specializationName) {
        Example<DoctorSpecialization> doctorSpecializationExample = Example.of(DoctorSpecialization.builder()
                        .name(specializationName)
                        .doctor(doctor)
                .build());

        Optional<DoctorSpecialization> doctorSpecializationOptional = doctorSpecializationRepository.findOne(doctorSpecializationExample);

        if(doctorSpecializationOptional.isEmpty()){
            throw new DoctorSpecializationNotFoundException("Nie znaleziono specjalizacji o nazwie [%s] dla podanego doktora".formatted(specializationName),HttpStatus.NOT_FOUND);
        }

        DoctorSpecialization doctorSpecializationToRemove = doctorSpecializationOptional.get();
        doctorSpecializationRepository.delete(doctorSpecializationToRemove);

        return doctorSpecializationToRemove;
    }
}
