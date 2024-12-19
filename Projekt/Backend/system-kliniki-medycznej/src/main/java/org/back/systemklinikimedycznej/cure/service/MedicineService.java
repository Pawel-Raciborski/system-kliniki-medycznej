package org.back.systemklinikimedycznej.cure.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.back.systemklinikimedycznej.cure.dto.AdvancedMedicineSearch;
import org.back.systemklinikimedycznej.cure.dto.MedicineDto;
import org.back.systemklinikimedycznej.cure.dto.MedicineListDto;
import org.back.systemklinikimedycznej.cure.dto.SearchMedicine;
import org.back.systemklinikimedycznej.cure.exception.MedicineException;
import org.back.systemklinikimedycznej.cure.mapper.MedicineMapper;
import org.back.systemklinikimedycznej.cure.repositories.MedicineRepository;
import org.back.systemklinikimedycznej.cure.repositories.entities.Medicine;
import org.back.systemklinikimedycznej.cure.web_client.MedicineWebClient;
import org.back.systemklinikimedycznej.model.Pagination;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.*;

import static org.back.systemklinikimedycznej.doctor.services.DoctorService.isNotEmptyAndBlank;

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

    public List<MedicineDto> searchMedicine(SearchMedicine searchMedicine, Pagination pagination) {
        MultiValueMap<String, String> queryParamsMap = new LinkedMultiValueMap<>();
        addPaginationToQueryParams(queryParamsMap,pagination);
        String medicinalProductName = searchMedicine.medicinalProductName();

        if(isNotEmptyAndBlank(medicinalProductName)){
            queryParamsMap.add("name",medicinalProductName);
        }
        AdvancedMedicineSearch advancedMedicineSearch = searchMedicine.advancedSearchOptions();

        if(Objects.nonNull(advancedMedicineSearch)){
            String gtinNumber = advancedMedicineSearch.gtinNumber();

            if(isNotEmptyAndBlank(gtinNumber)){
                queryParamsMap.add("eanGtin",gtinNumber);
            }

            String atcCode = advancedMedicineSearch.atcCode();

            if(isNotEmptyAndBlank(atcCode)){
                queryParamsMap.add("atcCode",atcCode);
            }

            String commonName = advancedMedicineSearch.commonName();

            if(isNotEmptyAndBlank(commonName)){
                queryParamsMap.add("commonName",commonName);
            }
        }

        MedicineListDto listDto = this.medicineWebClient.searchMedicines(queryParamsMap);

        return listDto.content();
    }

    private void addPaginationToQueryParams(MultiValueMap<String, String> queryParamsMap, Pagination pagination) {
        queryParamsMap.add("page",pagination.page().toString());
        queryParamsMap.add("size",pagination.pageSize().toString());
    }
}
