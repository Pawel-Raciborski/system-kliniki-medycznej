package org.back.systemklinikimedycznej.doctor.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.back.systemklinikimedycznej.doctor.controller.dto.DoctorsInfo;
import org.back.systemklinikimedycznej.doctor.repositories.DoctorRepository;
import org.back.systemklinikimedycznej.doctor.repositories.DoctorSpecializationRepository;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.back.systemklinikimedycznej.doctor.util.DoctorManagerUtil;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DoctorSearchingService {
    private final DoctorRepository doctorRepository;
    private final DoctorSpecializationRepository doctorSpecializationRepository;

    private Sort.Direction getDirection(String[] sortByWithOrder) {
        String order = sortByWithOrder[1];
        Sort.Direction direction;

        if (order.isEmpty()) {
            direction = Sort.Direction.ASC;
        } else {
            direction = Sort.Direction.fromString(order);
        }
        return direction;
    }

    public DoctorsInfo findAllPaged(Integer page, Integer pageSize, String sort) {
        String[] sortByWithOrder = sort.split(",");
        Sort.Direction direction = getDirection(sortByWithOrder);

        Pageable pageable = PageRequest.of(page, pageSize, direction, sortByWithOrder[0]);

        List<Doctor> pagedDoctors = doctorRepository.findAll(pageable).getContent();

        return DoctorManagerUtil.wrapIntoDoctorsInfo(pagedDoctors);
    }

    public DoctorsInfo findPagedWithSpecialization(String specializationName, Integer page, Integer pageSize) {
        log.info("Searching doctors with specialization: [{}]", specializationName);
        Pageable pageable = PageRequest.of(page, pageSize);

        List<Doctor> doctorsWithSpecialization = doctorSpecializationRepository.findPagedDoctorsWithSpecialization(specializationName, pageable).getContent();
        log.info("Found {} doctors", doctorsWithSpecialization.size());
        return DoctorManagerUtil.wrapIntoDoctorsInfo(doctorsWithSpecialization);
    }
}
