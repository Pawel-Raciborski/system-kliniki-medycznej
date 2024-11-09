package org.back.systemklinikimedycznej.cure.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.back.systemklinikimedycznej.cure.dto.MedicineDto;
import org.back.systemklinikimedycznej.cure.dto.MedicineListDto;
import org.back.systemklinikimedycznej.cure.exception.MedicineException;
import org.back.systemklinikimedycznej.cure.mapper.MedicineMapper;
import org.back.systemklinikimedycznej.cure.repositories.MedicineRepository;
import org.back.systemklinikimedycznej.cure.repositories.entities.Medicine;
import org.back.systemklinikimedycznej.cure.web_client.MedicineWebClient;
import org.back.systemklinikimedycznej.prescription.dto.PrescriptionMedicineDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MedicineService {
    private final MedicineWebClient medicineWebClient;
    private final MedicineRepository medicineRepository;


    @Transactional
    public Medicine findByRegistryNumber(String registryNumber) {
        Optional<Medicine> medicine = medicineRepository.findByRegistryNumber(registryNumber);
        return medicine.orElseGet(() -> findMedicineByWebClient(registryNumber));

    }


    public Medicine findMedicineByWebClient(String registryNumber) {
        MedicineListDto medicineList = medicineWebClient.findByRegistryNumber(registryNumber);

        List<MedicineDto> medicine = medicineList.content();

        if(medicine.isEmpty()){
            throw new MedicineException("Nie znaleziono leku z numerem pozwolenia: %s".formatted(registryNumber), HttpStatus.NOT_FOUND);
        }

        Medicine foundWebClientMedicine = MedicineMapper.INSTANCE.mapFromApiDto(medicine.getFirst());
        return medicineRepository.save(foundWebClientMedicine);
    }

}
